package com.software.impact.models;



import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class ParallelGateway extends Event {

    public String id;
    public List<String > incomingList=new ArrayList<>();
    public List<String > outgoingList=new ArrayList<>();

    @Override
    public List<Dependence> calcul(List<Task> taskList, BigDecimal p, List<Event>events,List<String> taskNameList) {
        List<Task> incomingTasks=new ArrayList<>();
        incomingTasks=getIncomingAllTask(taskList,this.getIncomingList(),events);
        List<Task> outGoingTasks=new ArrayList<>();
        this.getOutgoingList().forEach(s -> {
            Task task=findByOutGoing(taskList,s);
            if(task != null){
                outGoingTasks.add(task);
            }
        });

        BigDecimal valeur=new BigDecimal(1).divide(new BigDecimal(outgoingList.size())).multiply(p);
        List<Dependence> dependences=new ArrayList<>();
        incomingTasks.forEach(task -> {
            outGoingTasks.forEach(task1 -> {
                Dependence dependence=new Dependence();
                dependence.setAct1(task.name);
                dependence.setAct2(task1.name);
                dependence.setValeur(valeur);
                dependences.add(dependence);
            });
        });
        Dependence dependence=new Dependence();
        return dependences;
    }

    private List<Task> getIncomingAllTask(List<Task> taskList,List<String> incomingList, List<Event> events) {
        List<Task> incomingTasks=new ArrayList<>();
        incomingList.forEach(s -> {
            Task task=findByIncoming(taskList,s);
            if(task != null){
                incomingTasks.add(task);
            }else{
                events.forEach(event -> {
                    if(event instanceof ParallelGateway && ((ParallelGateway) event).getId().equals(s)){
                        incomingTasks.addAll(getIncomingAllTask(taskList,((ParallelGateway) event).getIncomingList(), events) );
                    }else if(event instanceof SequenceFlow && ((SequenceFlow) event).getId().equals(s)){
                      List<String> incom=new ArrayList<>();
                      incom.add(((SequenceFlow) event).getSourceRef());
                        incomingTasks.addAll(getIncomingAllTask(taskList,incom, events));
                    }
                });
            }
        });
        return incomingTasks;
    }

    public Task findByIncoming(List<Task> tasks,String incoming){
       // for (SequenceFlow sequenceFlow : sequenceFlows) {
        //    if(sequenceFlow.getId().equals(incoming)){
         //       return sequenceFlow.findByOutGoing(tasks,sequenceFlow.getId());
         //   }
       // }
       // return null;
        for (Task task : tasks) {
            if(task.getOutgoingList().contains(incoming)){
                return task;
            }
        }
        return null;
    }

    public Task findByOutGoing(List<Task> tasks, String outgoing){
        //for (SequenceFlow sequenceFlow : sequenceFlows) {
        //    if(sequenceFlow.getId().equals(outgoing)){
        //        return sequenceFlow.findByIncoming(tasks,sequenceFlow.getId());
        //    }
       // }
       // return null;
        for (Task task : tasks) {
            if(task.getIncomingList().contains(outgoing)){
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
    
    
    
}
