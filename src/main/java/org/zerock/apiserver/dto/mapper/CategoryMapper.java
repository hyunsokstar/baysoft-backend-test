// 파일 경로: src/main/java/org/zerock/apiserver/dto/mapper/CategoryMapper.java
package org.zerock.apiserver.dto.mapper;

import org.zerock.apiserver.domain.Category;
import org.zerock.apiserver.dto.CategoryDto;

public class CategoryMapper {

    public static CategoryDto entityToDto(Category category) {
        if (category == null) {
            return null;
        }

        return CategoryDto.builder()
                .categoryId(category.getCategoryId())
                .name(category.getName())
                .regDt(category.getRegDt())
                .uptDt(category.getUptDt())
                .build();
    }

    public static Category dtoToEntity(CategoryDto dto) {
        if (dto == null) {
            return null;
        }

        return Category.builder()
                .categoryId(dto.getCategoryId())
                .name(dto.getName())
                .regDt(dto.getRegDt())
                .uptDt(dto.getUptDt())
                .build();
    }
}
