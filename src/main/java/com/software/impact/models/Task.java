package com.software.impact.models;



import java.util.ArrayList;
import java.util.List;


public class Task {

    public String id;
    public String name;
    public List<String > incomingList=new ArrayList<>();
    public List<String > outgoingList=new ArrayList<>();
    public int id_matrice; // l'index de task dans la mtrice de cout
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getIncomingList() {
		return incomingList;
	}
	public void setIncomingList(List<String> incomingList) {
		this.incomingList = incomingList;
	}
	public List<String> getOutgoingList() {
		return outgoingList;
	}
	public void setOutgoingList(List<String> outgoingList) {
		this.outgoingList = outgoingList;
	}
	public int getId_matrice() {
		return id_matrice;
	}
	public void setId_matrice(int id_matrice) {
		this.id_matrice = id_matrice;
	}
    
    
}
