package com.indra.notesspringboot.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Note {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Integer id;
	private String titulo;
	private String descripcion;
	@DateTimeFormat(iso = ISO.DATE_TIME)
	@JsonFormat(pattern = "YYYY-MM-dd HH:mm") 
	private LocalDateTime timestamp;
	private Boolean isFavorite;
	
	public Note() {
		
	}
	
	
	public Note(String titulo, String descripcion, Boolean isFavorite) {		
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.timestamp = LocalDateTime.now();
		this.isFavorite = isFavorite;
	}


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	public Boolean getIsFavorite() {
		return isFavorite;
	}
	public void setIsFavorite(Boolean isFavorite) {
		this.isFavorite = isFavorite;
	}

	/*@Override
	public String toString() {
		return "{"
				+ "\"id\": "+id+",\n"
				+ "\"titulo\": "+"\""+titulo+"\""+",\n"
				+ "\"descripcion\": "+"\""+descripcion+"\""+",\n"
				+ "\"timestamp\": "+"\""+timestamp.toString()+"\""+",\n"
				+ "\"isFavorite\": "+isFavorite+",\n"
				+ "}";
	}*/
}
