package com.blog_assessment.blog.mappers;

public interface SimpleEntityMapper<E, D> {

    D mapToDto(E entity);

    E mapToEntity(D dto);

}