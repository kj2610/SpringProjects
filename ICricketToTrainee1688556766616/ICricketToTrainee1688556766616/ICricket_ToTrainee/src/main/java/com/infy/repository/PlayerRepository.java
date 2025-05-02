package com.infy.repository;
import java.awt.print.Pageable;
import java.util.List;
import java.util.SortedMap;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.infy.entity.Player;

public interface PlayerRepository extends PagingAndSortingRepository<Player, Integer> {
	
	
	List<Player> getAllPlayersByDebutDateAfter(String date, org.springframework.data.domain.Pageable pageable);
	
	List<Player> getAllPlayersByCountry(String country, Sort sort);

}