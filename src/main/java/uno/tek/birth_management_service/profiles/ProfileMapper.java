package uno.tek.birth_management_service.profiles;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    @Mapping(target = "isActive", ignore = true)
    Profile toEntity(ProfileDTO dto);

    @Mapping(target = "password", ignore = true)
    ProfileDTO toDto(Profile entity);
}
