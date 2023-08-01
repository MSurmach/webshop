package com.intexsoft.webshop.feedbackservice.service.impl;

import com.intexsoft.webshop.feedbackservice.dto.UsersReplicaDto;
import com.intexsoft.webshop.feedbackservice.mapper.UserReplicaMapper;
import com.intexsoft.webshop.feedbackservice.model.UsersReplica;
import com.intexsoft.webshop.feedbackservice.repository.UsersReplicaRepository;
import com.intexsoft.webshop.feedbackservice.service.UsersReplicaService;
import com.intexsoft.webshop.messagecommon.event.user.UserCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.intexsoft.webshop.feedbackservice.util.JsonUtils.getAsString;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsersReplicaServiceImpl implements UsersReplicaService {
    private final UsersReplicaRepository usersReplicaRepository;
    private final UserReplicaMapper userReplicaMapper;

    @Override
    public UsersReplicaDto createUsersReplica(UserCreatedEvent userCreatedEvent) {
        log.info("IN: trying to save a new user replica by the event message  = {}", getAsString(userCreatedEvent));
        UsersReplica savedUsersReplica = usersReplicaRepository.save(userReplicaMapper.toUsersReplica(userCreatedEvent));
        log.info("OUT: new users replica saved successfully. Saved user replica details = {}", getAsString(savedUsersReplica));
        return userReplicaMapper.toUsersReplicaDto(savedUsersReplica);
    }
}