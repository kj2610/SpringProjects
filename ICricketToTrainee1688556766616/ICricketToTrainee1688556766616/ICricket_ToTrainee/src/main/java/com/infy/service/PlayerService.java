package com.infy.service;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.infy.dto.PlayerDTO;
import com.infy.exception.InfyPlayerException;

public interface PlayerService {

	public List<PlayerDTO> getFirstSevenPlayers(int pageNo, int pageSize) throws InfyPlayerException;

	public List<PlayerDTO> getAllPlayersByDebutDateAfter(String debutDate, int i, int j) throws InfyPlayerException;

	public List<PlayerDTO> getAllPlayersSortedByRanking(Sort sort) throws InfyPlayerException;

	public List<PlayerDTO> getAllPlayersOfCountrySortedByRanking(String country, Sort sort) throws InfyPlayerException;
}