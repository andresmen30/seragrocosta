/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.ejb.facade;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.persistence.EmbeddedId;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortOrder;

/**
 *
 * @author USER
 * @param <T>
 */
public abstract class AbstractFacade<T> {

    @PersistenceContext
    private EntityManager entityManager;

    private final Class<T> entityClass;

    public AbstractFacade(final Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public List<T> searchFilters(final int primero, final int tamanoPagina, final String ordenCampo,
            final SortOrder tipoOrden, final Map<String, FilterMeta> filtros) {
        final CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        final CriteriaQuery<T> consulta = criteriaBuilder.createQuery(entityClass);
        final Root<T> from = consulta.from(entityClass);
        final Predicate condicionFiltros = this.addFilters(criteriaBuilder, from, filtros);
        consulta.select(from);
        if (null != tipoOrden) {
            consulta.orderBy(this.orderField(from, ordenCampo,
                    consulta, tipoOrden, criteriaBuilder));
        }
        consulta.where(condicionFiltros);
        final TypedQuery<T> tipoConsulta = this.entityManager.createQuery(consulta);
        if (tamanoPagina >= 0) {
            tipoConsulta.setMaxResults(tamanoPagina);
        }
        if (primero >= 0) {
            tipoConsulta.setFirstResult(primero);
        }
        try {
            return tipoConsulta.getResultList();
        } catch (Exception e) {
            e.getMessage();
            return new ArrayList<>();
        }
    }

    private List<Order> orderField(final Root<T> from, final String ordenCampo,
            final CriteriaQuery<T> consulta,
            final SortOrder tipoOrden, final CriteriaBuilder criteriaBuilder) {
        String campoBusqueda = ordenCampo;
        if (StringUtils.isBlank(campoBusqueda)) {
            campoBusqueda = this.getNamePrimaryKey(this.entityClass);
        }
        final Path<String> pathCompuesto = this.getPathCompuesto(from, campoBusqueda);
        consulta.orderBy(new Order[]{tipoOrden.equals(SortOrder.ASCENDING)
            ? criteriaBuilder.asc(pathCompuesto)
            : criteriaBuilder.desc(pathCompuesto)});
        return consulta.getOrderList();
    }

    private String getNamePrimaryKey(final Class<T> entity) {
        for (final Field field : entity.getDeclaredFields()) {
            if (field.isAnnotationPresent(Id.class)
                    || field.isAnnotationPresent(EmbeddedId.class)) {
                return field.getName();
            }
        }
        return null;
    }

    private Predicate addFilters(final CriteriaBuilder criteriaBuilder,
            final Root<T> from, final Map<String, FilterMeta> filtros) {
        Predicate filterCondition = criteriaBuilder.conjunction();
        for (final Map.Entry<String, FilterMeta> filter : filtros.entrySet()) {
            final Path<String> pathFilter = this.getPathCompuesto(from, filter.getKey());
            if (null != filter.getValue().getFilterValue()) {
                if (filter.getValue().getFilterValue() instanceof Number) {
                    filterCondition = criteriaBuilder.and(filterCondition, criteriaBuilder.equal(pathFilter, filter.getValue().getFilterValue()));
                } else if (filter.getValue().getFilterValue() instanceof String) {
                    final String value = (String) filter.getValue().getFilterValue();
                    filterCondition = criteriaBuilder.and(filterCondition,
                            criteriaBuilder.like(criteriaBuilder.upper(pathFilter.as(String.class)), "%" + value.toUpperCase() + "%"));
                } else if (filter.getValue().getFilterValue() instanceof Collection) {
                    filterCondition = this.collectionFilter(from, criteriaBuilder, filter);
                } else if (filter.getValue().getFilterValue() instanceof Date) {
                    filterCondition = this.dateFilter(from, criteriaBuilder, filter);
                } else {
                    filterCondition = criteriaBuilder.and(filterCondition, criteriaBuilder.equal(pathFilter, filter.getValue().getFilterValue()));
                }
            }
        }
        return filterCondition;
    }

    private Predicate dateFilter(final Root<T> from, final CriteriaBuilder criteriaBuilder, final Map.Entry<String, FilterMeta> filter) {
        Predicate filterCondition = criteriaBuilder.conjunction();
        final Path<Date> path = (Path<Date>) this.getPathCompuestoDate(from, filter.getKey());
        final Calendar cal = Calendar.getInstance();
        cal.setTime((Date) filter.getValue().getFilterValue());
        cal.add(6, 1);
        final Calendar callIni = Calendar.getInstance();
        callIni.setTime((Date) filter.getValue().getFilterValue());
        cal.set(11, 0);
        final Predicate fechaInicialPredicate = criteriaBuilder.between(path, callIni.getTime(), cal.getTime());
        filterCondition = criteriaBuilder.and(filterCondition, fechaInicialPredicate);
        return filterCondition;
    }

    private Predicate collectionFilter(final Root<T> from, final CriteriaBuilder criteriaBuilder, final Map.Entry<String, FilterMeta> filter) {
        Predicate filterCondition = criteriaBuilder.conjunction();
        final Path<String> pathFilter = this.getPathCompuesto(from, filter.getKey());
        final Collection<?> valorFiltro = (Collection<?>) filter.getValue().getFilterValue();
        final Boolean isValidate = !valorFiltro.isEmpty();
        if (isValidate) {
            final Iterator<?> iterator = valorFiltro.iterator(); 
            final Object primerValor = iterator.next();
            if (primerValor instanceof Date) {
                final Path<Date> filtroPath = from.get(filter.getKey()); 
                final Date fechaInicial = (Date) primerValor;
                final Date fechaFinal = (Date) iterator.next();
                final Predicate fechaInicialPredicate = criteriaBuilder.greaterThanOrEqualTo(filtroPath, fechaInicial);
                final Predicate fechaFinalPredicate = criteriaBuilder.lessThanOrEqualTo(filtroPath, fechaFinal);
                filterCondition = criteriaBuilder.and(new Predicate[]{filterCondition, fechaInicialPredicate, fechaFinalPredicate});
            } else {
                filterCondition = criteriaBuilder.and(filterCondition, pathFilter.in(((Collection) filter.getValue().getFilterValue()).toArray()));
            }
        } else if (filter.getValue().getFilterValue().getClass().isArray()) {
            final Object[] value = (Object[]) filter.getValue().getFilterValue();
            final List<Object> filtroColeccion = Arrays.asList(value);
            if (!filtroColeccion.isEmpty()) {
                if (pathFilter.getJavaType().equals(List.class)) {
                    for (final Object object : filtroColeccion) {
                        final Path<? extends Collection> filtroPath2 = from.get(filter.getKey());
                        final Predicate predicate = criteriaBuilder.isMember(object, filtroPath2);
                        filterCondition = criteriaBuilder.and(filterCondition, predicate);
                    }
                } else {
                    filterCondition = criteriaBuilder.and(filterCondition, pathFilter.in(filtroColeccion));
                }
            }
        } else if (filter.getValue().getFilterValue() instanceof Long || filter.getValue().getFilterValue() instanceof Integer) {
            final Number value2 = (Number) filter.getValue().getFilterValue();
            filterCondition = criteriaBuilder.and(filterCondition, criteriaBuilder.equal(pathFilter, value2));
        } else if (filter.getValue().getFilterValue() instanceof Date) {
            filterCondition = this.dateFilter(from, criteriaBuilder, filter);
        } else {
            filterCondition = criteriaBuilder.and(filterCondition, criteriaBuilder.equal(pathFilter, filter.getValue().getFilterValue()));
        }
        return filterCondition;
    }

    public Long registersNumber(final Map<String, FilterMeta> filtrosRegistros) {
        final CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        final CriteriaQuery<Long> query = (CriteriaQuery<Long>) criteriaBuilder.createQuery((Class) Long.class);
        final Root<T> from = (Root<T>) query.from(entityClass);
        query.select(criteriaBuilder.count(from));
        final Predicate condicionFiltros = this.addFilters(criteriaBuilder, from, filtrosRegistros);
        query.where(condicionFiltros);
        return this.entityManager.createQuery(query).getSingleResult();
    }

    private Path<String> getPathCompuesto(final Root<T> from, final String campo) {
        Path<String> filtroPath = null;
        if (campo.contains(".")) {
            for (final String atributo : campo.split("\\.")) {
                if (null == filtroPath) {
                    filtroPath = from.get(atributo);
                } else {
                    filtroPath = filtroPath.get(atributo);
                }
            }
        } else {
            filtroPath = from.get(campo);
        }
        return filtroPath;
    }

    private Path<T> getPathCompuestoDate(final Root<T> from, final String campo) {
        Path<T> filtroPath = null;
        if (campo.contains(".")) {
            for (final String atributo : campo.split("\\.")) {
                if (null == filtroPath) {
                    filtroPath = from.get(atributo);
                } else {
                    filtroPath = filtroPath.get(atributo);
                }
            }
        } else {
            filtroPath = (Path<T>) from.get(campo);
        }
        return filtroPath;
    }

    public void create(final T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(final T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(final T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(final Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(final int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

}
