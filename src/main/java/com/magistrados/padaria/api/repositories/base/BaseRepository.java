package com.magistrados.padaria.api.repositories.base;

public interface BaseRepository<T, I> {
    public T consultar(I id);
}
