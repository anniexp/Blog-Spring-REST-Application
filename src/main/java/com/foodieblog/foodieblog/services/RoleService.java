package com.foodieblog.foodieblog.services;

import com.foodieblog.foodieblog.dtos.RoleGetDto;
import com.foodieblog.foodieblog.entities.Role;
import com.foodieblog.foodieblog.exceptionHandlers.RoleNotFoundException;
import com.foodieblog.foodieblog.mapstruct.mappers.MapStructMapper;
import com.foodieblog.foodieblog.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    private final RoleRepository roleRepository;
    private final MapStructMapper mapStructMapper;

    @Autowired
    public RoleService(RoleRepository roleRepository, MapStructMapper mapStructMapper) {
        this.roleRepository = roleRepository;
        this.mapStructMapper = mapStructMapper;
    }

    public void save(RoleGetDto roleGetDto) {
        Role role = mapStructMapper.roleDtoToRole(roleGetDto);
        roleRepository.save(role);
    }

    public Optional<Role> findById(long id) {
        return roleRepository.findById(id);
    }

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public Role findByRoleName(String string) throws RoleNotFoundException {
       List<Role> roleList = roleRepository.findByRoleName(string);
       if (roleList.size() == 0) {
           throw new RoleNotFoundException("Role not found");
       }

      return roleList.get(0);
    }
}
