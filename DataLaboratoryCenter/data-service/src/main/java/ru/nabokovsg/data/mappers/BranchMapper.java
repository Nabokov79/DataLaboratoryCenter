package ru.nabokovsg.data.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.data.dto.branch.BranchDto;
import ru.nabokovsg.data.dto.branch.NewBranchDto;
import ru.nabokovsg.data.dto.branch.ShortBranchDto;
import ru.nabokovsg.data.dto.branch.UpdateBranchDto;
import ru.nabokovsg.data.models.Branch;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface BranchMapper {

    Branch mapToNewBranch(NewBranchDto branchDto);

    Branch mapToUpdateBranch(UpdateBranchDto branchDto);

    BranchDto mapToBranchDto(Branch branch);

    List<ShortBranchDto> mapToShortBranchDto(Set<Branch> branches);

    Branch mapToBranch(BranchDto branchDto);
}