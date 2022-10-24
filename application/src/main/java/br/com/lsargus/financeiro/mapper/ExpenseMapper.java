package br.com.lsargus.financeiro.mapper;

import br.com.lsargus.financeiro.data.ExpenseBO;
import br.com.lsargus.financeiro.dto.ExpenseDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

public class ExpenseMapper extends ModelMapper {

    public ExpenseMapper() {
        TypeMap<ExpenseBO, ExpenseDTO> mapperBoToDto = this.createTypeMap(ExpenseBO.class, ExpenseDTO.class);
        mapperBoToDto.addMapping(src -> src.getCategory().getId(), ExpenseDTO::setExpenseCategoryId);
        mapperBoToDto.addMapping(src -> src.getCategory().getDescription(), ExpenseDTO::setExpenseCategoryDescription);

        TypeMap<ExpenseDTO, ExpenseBO> mapperDtoToBo = this.createTypeMap(ExpenseDTO.class, ExpenseBO.class);
        mapperDtoToBo.<Long>addMapping(ExpenseDTO::getExpenseCategoryId, (dest, value) -> dest.getCategory().setId(value));
        mapperDtoToBo.<String>addMapping(ExpenseDTO::getExpenseCategoryDescription, (dest, value) -> dest.getCategory().setDescription(value));
    }
}
