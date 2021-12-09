package com.software.impact.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.software.impact.services.TaskService;


public class SequenceFlow extends Event{
    public String id;
    public String sourceRef;
    public String targetRef;

    @Autowired
    TaskService taskService;

    @Override
    public List<Dependence> calcul(List<Task> taskList, BigDecimal p, List<Event>events,List<String> taskNameList) {
        Task taskIncomoing=this.findByIncoming(taskList,this.getId());
        Task taskOutgoing=this.findByOutGoing(taskList,this.getId());
        List<Dependence> dependences=new ArrayList<>();
        if(taskIncomoing != null && taskOutgoing != null) {
            Dependence dependence = new Dependence();
            dependence.setAct1(taskIncomoing.name);
            dependence.setAct2(taskOutgoing.name);
            dependence.valeur = p;
            dependences.add(dependence);
        }
        return dependences;
    }

    public Task findByIncoming(List<Task> tasks,String incoming){
        for (Task task : tasks) {
            if(task.getIncomingList().contains(incoming)){
                return task;
            }
        }
        return null;
    }

    public Task findByOutGoing(List<Task> tasks, String outgoing){
        for (Task task : tasks) {
            if(task.getOutgoingList().contains(outgoing)){
                return task;
            }
        }
        return null;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSourceRef() {
		return sourceRef;
	}

	public void setSourceRef(String sourceRef) {
		this.sourceRef = sourceRef;
	}

	public String getTargetRef() {
		return targetRef;
	}

	public void setTargetRef(String targetRef) {
		this.targetRef = targetRef;
	}

	
    
    
}
