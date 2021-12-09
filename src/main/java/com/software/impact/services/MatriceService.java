package com.software.impact.services;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.software.impact.models.Event;
import com.software.impact.models.ExclusiveGateway;
import com.software.impact.models.InclusiveGateway;
import com.software.impact.models.ParallelGateway;
import com.software.impact.models.SequenceFlow;
import com.software.impact.models.Task;

@Service
public class MatriceService {
    String [][] matrice;
    //List<Event> events=new ArrayList<>();
    //List<Task> alltasks;
    //List<String> taskList=new ArrayList<>();
    //List<Task> allOutTasktasks=new ArrayList<>();
    Task currentTask=null;

    public void readModel() {
        try {
            File fXmlFile = new File("src/main/resources/Bicycle.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            Node node = doc.getElementsByTagName("bpmn:process").item(0);
            NodeList nList=node.getChildNodes();
            //alltasks=new ArrayList<>();
            for (int temp = 1; temp < nList.getLength(); temp+=2) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;
                    String tagName=eElement.getTagName().substring(5);
                    if(tagName.equals("task")){
                        String name=eElement.getAttribute("name");
                        Task task=new Task();
                        task.setId(eElement.getAttribute("id"));
                        task.setName(name.substring(0,name.indexOf(":")));
                        NodeList incomingList=eElement.getElementsByTagName("bpmn:incoming");
                        for(int i = 0;i<incomingList.getLength();i++){
                            task.getIncomingList().add(incomingList.item(i).getFirstChild().getNodeValue());
                        }
                        NodeList outgoingList=eElement.getElementsByTagName("bpmn:outgoing");
                        for(int i = 0;i<outgoingList.getLength();i++){
                            task.getOutgoingList().add(outgoingList.item(i).getFirstChild().getNodeValue());
                        }
                        task.setId_matrice(Utils.allTasks.size());
                        Utils.allTasks.add(task);
                    }else if(tagName.equals("exclusiveGateway")) {
                        NodeList incomingList = eElement.getElementsByTagName("bpmn:incoming");
                        Event event=new ExclusiveGateway();
                        ((ExclusiveGateway)event).setId(eElement.getAttribute("id"));
                        for (int i = 0; i < incomingList.getLength(); i++) {
                            ((ExclusiveGateway)event).getIncomingList().add(incomingList.item(i).getFirstChild().getNodeValue());
                        }
                        NodeList outgoingList = eElement.getElementsByTagName("bpmn:outgoing");
                        for (int i = 0; i < outgoingList.getLength(); i++) {
                            ((ExclusiveGateway)event).getOutgoingList().add(outgoingList.item(i).getFirstChild().getNodeValue());
                        }
                        Utils.events.add(event);
                    }else if(tagName.equals("inclusiveGateway")) {
                        NodeList incomingList = eElement.getElementsByTagName("bpmn:incoming");
                        Event event=new InclusiveGateway();
                        ((InclusiveGateway)event).setId(eElement.getAttribute("id"));
                        for (int i = 0; i < incomingList.getLength(); i++) {
                            ((InclusiveGateway)event).getIncomingList().add(incomingList.item(i).getFirstChild().getNodeValue());
                        }
                        NodeList outgoingList = eElement.getElementsByTagName("bpmn:outgoing");
                        for (int i = 0; i < outgoingList.getLength(); i++) {
                            ((InclusiveGateway)event).getOutgoingList().add(outgoingList.item(i).getFirstChild().getNodeValue());
                        }
                        Utils.events.add(event);
                    } else if(tagName.equals("parallelGateway")) {
                        NodeList incomingList = eElement.getElementsByTagName("bpmn:incoming");
                        Event event=new ParallelGateway();
                        ((ParallelGateway)event).setId(eElement.getAttribute("id"));
                        for (int i = 0; i < incomingList.getLength(); i++) {
                            ((ParallelGateway)event).getIncomingList().add(incomingList.item(i).getFirstChild().getNodeValue());
                        }
                        NodeList outgoingList = eElement.getElementsByTagName("bpmn:outgoing");
                        for (int i = 0; i < outgoingList.getLength(); i++) {
                            ((ParallelGateway)event).getOutgoingList().add(outgoingList.item(i).getFirstChild().getNodeValue());
                        }
                        Utils.events.add(event);
                    }
                    else if(tagName.equals("sequenceFlow")){
                        Event event=new SequenceFlow();
                        ((SequenceFlow)event).setId(eElement.getAttribute("id"));
                        ((SequenceFlow)event).setSourceRef(eElement.getAttribute("sourceRef"));
                        ((SequenceFlow)event).setTargetRef(eElement.getAttribute("targetRef"));
                        Utils.events.add(event);
                    }
                }
            }
           // this.calcul(Utils.events,alltasks);
            Utils.initiateEventLists();
            Utils.printTasks();
            Utils.printEvents();
            
            double[][] matrice = Utils.initiateMatrix();
            matrice = Utils.calculateCosts(matrice);
            Utils.printMatrice(matrice);

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    /*public void calcul(List<Event> events,List<Task> tasks){
        this.matrice= new String[tasks.size()+1][tasks.size()+1];
        tasks.forEach(task -> {
            taskList.add(task.name);
            this.matrice[0][taskList.size()]=task.name;
            this.matrice[taskList.size()][0]=task.name;
        });
        for (int j =1;j<matrice.length;j++){
            this.matrice[j][j]=String.valueOf(0);
        }
        //List<SequenceFlow> sequenceFlows=new ArrayList<>();
        //events.forEach(event -> {
        //    if (event instanceof SequenceFlow){
        //        sequenceFlows.add((SequenceFlow)event);
        //    }
        //});
        events.forEach(event -> {
            List<Dependence> dependences=event.calcul(tasks, new BigDecimal(0.5),events,taskList);
            dependences.forEach(dependence -> {
                int i=taskList.indexOf(dependence.getAct1());
                int j=taskList.indexOf(dependence.getAct2());
                this.matrice[i+1][j+1]=String.valueOf(dependence.getValeur());
                this.matrice[j+1][i+1]=String.valueOf(dependence.getValeur());
            });
            if(event instanceof ExclusiveGateway){
                List<Task> outGoingTasks=new ArrayList<>();
                outGoingTasks=getAllOutGoingTask(tasks,((ExclusiveGateway)event).getOutgoingList(),events);
                for (Task outGoingTask1 : outGoingTasks) {
                    for (Task outGoingTask2 : outGoingTasks) {
                        {
                            if(! outGoingTask1.getName().equals(outGoingTask2.getName())){
                                int i=taskList.indexOf(outGoingTask1.getName());
                                int j=taskList.indexOf(outGoingTask2.getName());
                                this.matrice[i+1][j+1]="-";
                                this.matrice[j+1][i+1]="-";
                            }
                        }
                    }
                }
            }


        });
        boolean incomplet=false;

        //do  {
        //    incomplet=false;
            for (int i = 0; i < taskList.size(); i++) {
                for (int j = 0; j < taskList.size(); j++) {
                    if (i != j && matrice[i + 1][j + 1] == null) {
                        matrice[i + 1][j + 1] = calculCout(i, j);
                        // String s1=taskList.get(i);
                        // String s2=taskList.get(j);

                        // for (String s3 : taskList) {
                        //     int index=taskList.indexOf(s3);
                        //     if( !s3.equals(s1) && !s3.equals(s2) && matrice[i+1][index+1] != null && matrice[j+1][index+1] != null){
                        //         if ((matrice[i+1][index+1]).equals("-") || (matrice[j+1][index+1]).equals("-")) {

                        //         }
                        //         else {
                        //             incomplet=true;
                        //             matrice[i + 1][j + 1] = String.valueOf(Double.parseDouble(matrice[j + 1][index + 1]) * Double.parseDouble(matrice[i + 1][index + 1]));
                        //             matrice[j + 1][i + 1] = String.valueOf(Double.parseDouble(matrice[j + 1][index + 1]) * Double.parseDouble(matrice[i + 1][index + 1]));
                        //       }
                        //   }
                        //   }
                        //if (fermer && matrice[i + 1][j + 1] == null && matrice[j + 1][i + 1]== null){
                        //    matrice[i+1][j+1]="-";
                        //    matrice[j+1][i+1]="-";
                        //}
                    }
                }
            }
        //    }
        //} while(incomplet);



            printMatrice(matrice);
    }*/

    /*private String calculCout(int i, int j)
        {
            if (matrice[i + 1][j + 1] != null) {
                return matrice[i + 1][j + 1];
            }
            Event event=null;
            List<Task> tasks=new ArrayList<>();
            tasks.forEach(task -> {
                if(task.name.equals(matrice[i + 1][0])){
                    currentTask=task;
                }
            });
            Utils.events.forEach(event1 -> {
                if (event1 instanceof SequenceFlow) {
                    if (currentTask.getIncomingList().contains(((SequenceFlow) event1).getTargetRef())) {
                        List<String> out = new ArrayList<>();
                        out.add(((SequenceFlow) event1).getTargetRef());
                        allOutTasktasks = this.getAllOutGoingTask(Utils.alltasks, out, Utils.events);
                    }
                } else if (event1 instanceof ExclusiveGateway) {
                    allOutTasktasks = getAllOutGoingTask(Utils.alltasks, ((ExclusiveGateway) event).getOutgoingList(), events);
                }
            });
            if(allOutTasktasks.size()==0){
                return "-";
            }
            else if(allOutTasktasks.size()==1){
                int index=taskList.indexOf(allOutTasktasks.get(0).getName());
                return String.valueOf(new BigDecimal(matrice[i][index+1]).multiply(new BigDecimal(calculCout(index,j))));
            }
            else if(allOutTasktasks.size()==2){
                int index1=taskList.indexOf(allOutTasktasks.get(0).getName());
                int index2=taskList.indexOf(allOutTasktasks.get(1).getName());

                if(new BigDecimal(matrice[i][index1+1]).multiply(new BigDecimal(calculCout(index1,j))).compareTo(new BigDecimal(0))<0 &&
                        new BigDecimal(matrice[i][index2+1]).multiply(new BigDecimal(calculCout(index2,j))).compareTo(new BigDecimal(0)) > 0) {
                   return String.valueOf(new BigDecimal(matrice[i][index2+1]).multiply(new BigDecimal(calculCout(index2,j))));
                }else if(new BigDecimal(matrice[i][index1+1]).multiply(new BigDecimal(calculCout(index1,j))).compareTo(new BigDecimal(0))>0 &&
                        new BigDecimal(matrice[i][index2+1]).multiply(new BigDecimal(calculCout(index2,j))).compareTo(new BigDecimal(0))< 0) {
                    return String.valueOf(new BigDecimal(matrice[i][index1+1]).multiply(new BigDecimal(calculCout(index1,j))));
                }else if(new BigDecimal(matrice[i][index1+1]).multiply(new BigDecimal(calculCout(index1,j))).compareTo(new BigDecimal(0))<0 &&
                        new BigDecimal(matrice[i][index2+1]).multiply(new BigDecimal(calculCout(index2,j))).compareTo(new BigDecimal(0))< 0) {
                        return "-";
                }
            }
            else {
                int index1=taskList.indexOf(allOutTasktasks.get(0).getName());
                int index2=taskList.indexOf(allOutTasktasks.get(1).getName());
                return String.valueOf(new BigDecimal(matrice[i][index2+1]).multiply(new BigDecimal(calculCout(index2,j)))
                        .min(new BigDecimal(matrice[i][index1+1]).multiply(new BigDecimal(calculCout(index1,j)))));
            }
            return "";
    }*/


   

    private List<Task> getAllOutGoingTask(List<Task> taskList,List<String> outGoing, List<Event> events) {
        List<Task> OutgoingTasks=new ArrayList<>();
        outGoing.forEach(s -> {
            Task task=findByOutGoing(taskList,s);
            if(task != null){
                OutgoingTasks.add(task);
            }else{
                events.forEach(event -> {
                    if(event instanceof ExclusiveGateway && ((ExclusiveGateway) event).getId().equals(s)){
                        OutgoingTasks.addAll(getAllOutGoingTask(taskList,((ExclusiveGateway) event).getOutgoingList(), events) );
                    }else if(event instanceof SequenceFlow && ((SequenceFlow) event).getId().equals(s)){
                        List<String> outcom=new ArrayList<>();
                        outcom.add(((SequenceFlow) event).getTargetRef());
                        OutgoingTasks.addAll(getAllOutGoingTask(taskList,outcom, events));
                    }
                });
            }
        });
        return OutgoingTasks;
    }

    public Task findByOutGoing(List<Task> tasks, String outgoing){
        for (Task task : tasks) {
            if(task.getIncomingList().contains(outgoing)){
                return task;
            }
        }
        return null;
    }

}
