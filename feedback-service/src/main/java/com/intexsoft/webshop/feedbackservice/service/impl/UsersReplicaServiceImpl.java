package com.intexsoft.webshop.feedbackservice.service.impl;

import com.intexsoft.webshop.feedbackservice.dto.UsersReplicaDto;
import com.intexsoft.webshop.feedbackservice.mapper.UserReplicaMapper;
import com.intexsoft.webshop.feedbackservice.model.UsersReplica;
import com.intexsoft.webshop.feedbackservice.repository.UsersReplicaRepository;
import com.intexsoft.webshop.feedbackservice.service.UsersReplicaService;
import com.intexsoft.webshop.messagecommon.event.user.UserCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersReplicaServiceImpl implements UsersReplicaService {
    private final UsersReplicaRepository usersReplicaRepository;
    private final UserReplicaMapper userReplicaMapper;

    @Override
    public UsersReplicaDto createUsersReplica(UserCreatedEvent userCreatedEvent) {
        UsersReplica savedUsersReplica = usersReplicaRepository.save(userReplicaMapper.toUsersReplica(userCreatedEvent));
        return userReplicaMapper.toUsersReplicaDto(savedUsersReplica);
    }
}