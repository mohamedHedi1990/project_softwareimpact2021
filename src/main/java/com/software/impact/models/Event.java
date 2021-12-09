package com.software.impact.models;

import java.math.BigDecimal;
import java.util.List;

public abstract class Event {

    public abstract List<Dependence> calcul(List<Task> taskList, BigDecimal p, List<Event>events,List<String> taskNameList);
}
