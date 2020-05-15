package corp.netizen.datastore.converters;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public interface GenericConverter<E,D>{

    E createFromDto(D dto);

    D createFromEntity(E entity);

    E updateEntity(E entity, D dto);

    default List createFromEntities(final Collection<E> entities) {
        return entities.stream()
                .map(this::createFromEntity)
                .collect(Collectors.toList());
    }

    default List createFromDtos(final Collection<D> dtos){
        return dtos.stream()
                .map(this::createFromDto)
                .collect(Collectors.toList());
    }

}
