package com.lessons.service.mapper;

public interface EntityMapper<Entity, Dto> {
    Dto toDto(Entity entity);
    Entity fromDto(Dto dto);
}
