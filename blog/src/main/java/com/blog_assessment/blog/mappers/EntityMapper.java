package com.blog_assessment.blog.mappers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

public interface EntityMapper<E, D> extends SimpleEntityMapper<E, D> {

    List<D> mapToDtos(List<E> entities);

    List<E> mapToEntities(List<D> Dtos);

    default Page<D> mappedToPagedDtos(Page<E> page){
        return new PageImpl<>(
                mapToDtos(page.getContent()),
                page.getPageable(),
                page.getTotalElements()
        );
    }
}

