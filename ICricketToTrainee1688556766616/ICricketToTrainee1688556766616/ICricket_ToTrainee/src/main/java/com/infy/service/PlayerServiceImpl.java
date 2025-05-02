package com.infy.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.metamodel.model.domain.MapPersistentAttribute;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.infy.dto.PlayerDTO;
import com.infy.entity.Player;
import com.infy.exception.InfyPlayerException;
import com.infy.repository.PlayerRepository;

@Service
public class PlayerServiceImpl implements PlayerService {

	@Autowired
	PlayerRepository playerRepository;
	
	@Autowired
	ModelMapper mapper;
	
	@Override
	public List<PlayerDTO> getFirstSevenPlayers(int pageNo, int pageSize) throws InfyPlayerException {
		PageRequest pageable = PageRequest.of(pageNo, pageSize);
		Page<Player> page = (Page<Player>) playerRepository.findAll(pageable);
		if(page.isEmpty()) {
			throw new InfyPlayerException("Service.NO_PLAYERS_IN_THIS_PAGE");
		}
		List<Player> players = page.getContent();
		List<PlayerDTO>  playerDTO = Arrays.asList(mapper.map(players, PlayerDTO[].class));
		return playerDTO;
	}

	@Override
	public List<PlayerDTO> getAllPlayersByDebutDateAfter(String debutDate, int i, int j) throws InfyPlayerException {
		Pageable pageable = PageRequest.of(i,j);
		List<Player> players = playerRepository.getAllPlayersByDebutDateAfter(debutDate, pageable);
		if(players.isEmpty()) {
			throw new InfyPlayerException("Service.NO_PLAYERS_IN_THIS_PAGE");
		}
		List<PlayerDTO> playerDTOs = Arrays.asList(mapper.map(players, PlayerDTO[].class)); 
		return playerDTOs;
	}

	@Override
	public List<PlayerDTO> getAllPlayersSortedByRanking(Sort sort) throws InfyPlayerException {
		Iterable<Player>players = playerRepository.findAll(sort);
		List<PlayerDTO>playersDTOs = new ArrayList<>();
		for(Player player : players) {
			 PlayerDTO dto = mapper.map(player, PlayerDTO.class);
			playersDTOs.add(dto);
		}
		if(playersDTOs.isEmpty()) {
			throw new InfyPlayerException("Service.NO_PLAYERS_IN_THIS_PAGE");
		}
		
		
		return playersDTOs;
	}

	@Override
	public List<PlayerDTO> getAllPlayersOfCountrySortedByRanking(String country, Sort sort) throws InfyPlayerException {
		List<Player> players = playerRepository.getAllPlayersByCountry(country, sort);
		if(players.isEmpty()) {
			throw new InfyPlayerException("Service.NO_PLAYERS_IN_THIS_PAGE");
		}
		List<PlayerDTO> playerDTOs = Arrays.asList(mapper.map(players, PlayerDTO[].class)); 
		return playerDTOs;
	}

}