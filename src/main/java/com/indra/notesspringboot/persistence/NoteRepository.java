package com.indra.notesspringboot.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.indra.notesspringboot.entity.Note;
@Repository
public interface NoteRepository extends JpaRepository<Note, Integer>{
	
	@Modifying
	@Query("UPDATE Note SET isFavorite=:isFavorite WHERE id=:id")
	void switchFavorite(@Param(value = "isFavorite") Boolean isFavorite,@Param(value = "id") Integer id);
}
