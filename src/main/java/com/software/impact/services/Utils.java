package com.software.impact.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.software.impact.models.Event;
import com.software.impact.models.ExclusiveGateway;
import com.software.impact.models.InclusiveGateway;
import com.software.impact.models.ParallelGateway;
import com.software.impact.models.SequenceFlow;
import com.software.impact.models.Task;

public class Utils {

	public static List<Task> allTasks = new ArrayList<Task>(); // IM CODING: la liste de toutes les tasks
	public static List<Event> events = new ArrayList<Event>(); // I CODING: la liste des évenements
	public static List<ExclusiveGateway> exclusiveGateWaysEvents = new ArrayList<ExclusiveGateway>();
	public static List<InclusiveGateway> inclusiveGateWaysEvents = new ArrayList<InclusiveGateway>();
	public static List<ParallelGateway> parallelGateWaysEvents = new ArrayList<ParallelGateway>();

	public static List<SequenceFlow> sequenceFlows = new ArrayList<SequenceFlow>();
	public static double p = 0.5; // IM CODING: la constante p

	public static List<Integer> excludedIndexes = new ArrayList<>();

	public static void initiateEventLists() {
		for (Event event : events) {
			if (event instanceof ExclusiveGateway) {
				ExclusiveGateway exclusiveGatwayEvent = (ExclusiveGateway) event;
				exclusiveGateWaysEvents.add(exclusiveGatwayEvent);
			} else if (event instanceof InclusiveGateway) {
				InclusiveGateway inclusiveGateway = (InclusiveGateway) event;
				inclusiveGateWaysEvents.add(inclusiveGateway);
			} else if (event instanceof ParallelGateway) {
				ParallelGateway parallelGateway = (ParallelGateway) event;
				parallelGateWaysEvents.add(parallelGateway);
			} else if (event instanceof SequenceFlow) {
				SequenceFlow sequenceFlow = (SequenceFlow) event;
				sequenceFlows.add(sequenceFlow);
			}
		}
	}

	public static void printTasks() {
		System.out.println(" --------------- TASK LIST BEGIN---------------");
		allTasks.stream().forEach(task -> {
			System.out.println(
					"TASK " + task.getName() + " | ID: " + task.getId() + " | INDEX MATRICE " + task.getId_matrice());
		});
		System.out.println(" --------------- TASK LIST END---------------");
	}

	private static String getIncomingFromEvent(Event event) {
		if (event instanceof ExclusiveGateway) {
			ExclusiveGateway event1 = (ExclusiveGateway) event;
			String output = "";
			for (int i = 0; i < event1.getIncomingList().size(); i++) {
				output = output + event1.getIncomingList().get(i);
				if (i != event1.getIncomingList().size() - 1) {
					output = output + " - ";
				}
			}
			return output;
		} else if (event instanceof InclusiveGateway) {
			InclusiveGateway event1 = (InclusiveGateway) event;
			String output = "";
			for (int i = 0; i < event1.getIncomingList().size(); i++) {
				output = output + event1.getIncomingList().get(i);
				if (i != event1.getIncomingList().size() - 1) {
					output = output + " - ";
				}
			}
			return output;
		} else if (event instanceof ParallelGateway) {
			ParallelGateway event1 = (ParallelGateway) event;
			String output = "";
			for (int i = 0; i < event1.getIncomingList().size(); i++) {
				output = output + event1.getIncomingList().get(i);
				if (i != event1.getIncomingList().size() - 1) {
					output = output + " - ";
				}
			}
			return output;
		}

		return null;

	}

	private static String getOutgoingFromEvent(Event event) {
		if (event instanceof ExclusiveGateway) {
			ExclusiveGateway event1 = (ExclusiveGateway) event;
			String output = "";
			for (int i = 0; i < event1.getOutgoingList().size(); i++) {
				output = output + event1.getOutgoingList().get(i);
				if (i != event1.getOutgoingList().size() - 1) {
					output = output + " - ";
				}
			}
			return output;
		} else if (event instanceof InclusiveGateway) {
			InclusiveGateway event1 = (InclusiveGateway) event;
			String output = "";
			for (int i = 0; i < event1.getOutgoingList().size(); i++) {
				output = output + event1.getOutgoingList().get(i);
				if (i != event1.getOutgoingList().size() - 1) {
					output = output + " - ";
				}
			}
			return output;
		} else if (event instanceof ParallelGateway) {
			ParallelGateway event1 = (ParallelGateway) event;
			String output = "";
			for (int i = 0; i < event1.getOutgoingList().size(); i++) {
				output = output + event1.getOutgoingList().get(i);
				if (i != event1.getOutgoingList().size() - 1) {
					output = output + " - ";
				}
			}
			return output;
		}

		return null;
	}

	public static void printEvents() {
		System.out.println(" --------------- EVENTS LIST BEGIN---------------");
		System.out.println(" --------------- SEQUENCE EVENTS LIST BEGIN---------------");
		sequenceFlows.stream().forEach(sf -> {
			System.out.println("SEQUENCE FLOW   | ID: " + sf.getId() + " | SOURCE REF " + sf.getSourceRef()
					+ " | TARGET REF " + sf.getTargetRef());
		});
		System.out.println(" --------------- SEQUENCE EVENTS LIST END---------------");

		System.out.println(" --------------- EXCLUSIVE GATEWAY EVENTS LIST BEGIN---------------");
		exclusiveGateWaysEvents.stream().forEach(event -> {
			System.out.println("EXCLUSIVE GATEWAY   | ID: " + event.getId() + " | INCOMING "
					+ getIncomingFromEvent(event) + " | OUTGOING " + getOutgoingFromEvent(event));
		});
		System.out.println(" --------------- EXCLUSIVE GATEWAY EVENTS LIST END---------------");

		System.out.println(" --------------- INCLUSIVE GATEWAY EVENTS LIST BEGIN---------------");
		inclusiveGateWaysEvents.stream().forEach(event -> {
			System.out.println("INCLUSIVE GATEWAY   | ID: " + event.getId() + " | INCOMING "
					+ getIncomingFromEvent(event) + " | OUTGOING " + getOutgoingFromEvent(event));
		});
		System.out.println(" --------------- INCLUSIVE GATEWAY EVENTS LIST END---------------");

		System.out.println(" --------------- PARALLEL GATEWAY EVENTS LIST BEGIN---------------");
		parallelGateWaysEvents.stream().forEach(event -> {
			System.out.println("PARALLEL GATEWAY   | ID: " + event.getId() + " | INCOMING "
					+ getIncomingFromEvent(event) + " | OUTGOING " + getOutgoingFromEvent(event));
		});
		System.out.println(" --------------- PARALLEL GATEWAY EVENTS LIST END---------------");

		System.out.println(" --------------- TASK LIST END---------------");
	}

	public static double[][] initiateMatrix() {
		double[][] matrice = new double[allTasks.size()][allTasks.size()];

		return matrice;
	}

	public static double[][] calculateCosts(double[][] matrice) {
		matrice = calculateDirectDependeciesFromEvent(matrice);
		matrice = calculateIndirectCosts(matrice);
		matrice = calculateAnotherPath(matrice);
		matrice = calculateInverseCosts(matrice);
		return matrice;
	}

	public static double[][] calculateInverseCosts(double[][] matrice) {
		for (int i = 0; i < allTasks.size(); i++) {
			for (int j = 0; j < allTasks.size(); j++) {
				if (matrice[i][j] != -2.0) {
					matrice[j][i] = matrice[i][j];
				} else {
					matrice[i][j] = matrice[j][i];
				}

			}
		}
		return matrice;
	}

	public static double[][] calculateDirectDependeciesFromEvent(double[][] matrice) {

		for (Event event : events) {
			if (event instanceof SequenceFlow) {
				SequenceFlow sequenceEvent = (SequenceFlow) event;
				matrice = calculateDependecyFromSequenceEvent(sequenceEvent, matrice);
			} else if (event instanceof ExclusiveGateway) {
				ExclusiveGateway exclusiveGatewayEvent = (ExclusiveGateway) event;
				matrice = calculateDependecyFromXOREvent(exclusiveGatewayEvent, matrice);

			} else if (event instanceof InclusiveGateway) {
				InclusiveGateway inclusiveGatewayEvent = (InclusiveGateway) event;
				matrice = calculateDependecyFromOREvent(inclusiveGatewayEvent, matrice);

			} else if (event instanceof ParallelGateway) {
				ParallelGateway parrallelGatewayEvent = (ParallelGateway) event;
				matrice = calculateDependecyFromANDEvent(parrallelGatewayEvent, matrice);

			}
		}
		// matrice = calculateIndirectCosts(matrice);

		return matrice;
	}

	public static double[][] calculateDependecyFromSequenceEvent(SequenceFlow sequenceEvent, double[][] matrice) {
		// IM CODING: Trouver le source et le target de l'evenement sequence
		String sourceTaskId = sequenceEvent.getSourceRef();
		String targetTaskId = sequenceEvent.getTargetRef();

		// IM CODING: Trouver les deux tasks source et target correspondant à
		// l'évenement
		Task sourceTask = allTasks.stream().filter(task -> task.getId().equals(sourceTaskId)).findFirst().orElse(null);
		Task targetTask = allTasks.stream().filter(task -> task.getId().equals(targetTaskId)).findFirst().orElse(null);

		// IM CODING: Modifier la cellule correspondant à ces deux tasks
		if (sourceTask != null && targetTask != null) {
			matrice[sourceTask.getId_matrice()][targetTask.getId_matrice()] = p;
			matrice[targetTask.getId_matrice()][sourceTask.getId_matrice()] = -2.0;
		}

		return matrice;
	}

	public static double[][] calculateDependecyFromXOREvent(ExclusiveGateway exclusiveGatewayEvent,
			double[][] matrice) {
		// IM CODING: Trouver la liste des incoming et outgoing de l'event
		List<String> incomings = exclusiveGatewayEvent.getIncomingList();
		List<String> outgoings = exclusiveGatewayEvent.getOutgoingList();

		// IM CODING: On suppose que l'incoming est toujours un seul flow
		String incmoing = incomings.get(0);

		// IM CODING: Trouver le task incoming
		Task incomingTask = allTasks.stream().filter(task -> task.getOutgoingList().contains(incmoing)).findFirst()
				.orElse(null);

		if (incomingTask != null) { // IM CODING: Le incoming est un task (et pas un outgoing d'un autre opérateur)
			List<Task> outgoingTasks = new ArrayList<Task>(); // IM CODING: initailiser la liste des tasks
			for (String outgoing : outgoings) {
				Task targetTask = allTasks.stream().filter(task -> task.getIncomingList().contains(outgoing))
						.findFirst().orElse(null);
				if (targetTask != null) {
					outgoingTasks.add(targetTask);
				}
			}
			// IM CODING: calculate dependecy between incoming task et outgoingTasks
			for (Task outgoingTask : outgoingTasks) {
				matrice[incomingTask.getId_matrice()][outgoingTask.getId_matrice()] = calculateDirectCostInXOREvent(
						outgoingTasks.size());
				matrice[outgoingTask.getId_matrice()][incomingTask.getId_matrice()] = -2.0;

			}

			// IM CODING: calculate dependecy between outgoing tasks: On suppose pour le
			// moment que l'outgoing est toujours deux pour l'evenement XOR
			// A ameliorer par ce que le nombre de branches peut dépasser 2
			if (outgoingTasks.size() == 2) {
				matrice[outgoingTasks.get(0).getId_matrice()][outgoingTasks.get(1).getId_matrice()] = -1.0;
				matrice[outgoingTasks.get(1).getId_matrice()][outgoingTasks.get(0).getId_matrice()] = -1.0;

			}

		}

		return matrice;
	}
	
	public static double[][] calculateDependecyFromOREvent(InclusiveGateway inclusiveGatewayEvent,
			double[][] matrice) {
		// IM CODING: Trouver la liste des incoming et outgoing de l'event
		List<String> incomings = inclusiveGatewayEvent.getIncomingList();
		List<String> outgoings = inclusiveGatewayEvent.getOutgoingList();

		// IM CODING: On suppose que l'incoming est toujours un seul flow
		String incmoing = incomings.get(0);

		// IM CODING: Trouver le task incoming
		Task incomingTask = allTasks.stream().filter(task -> task.getOutgoingList().contains(incmoing)).findFirst()
				.orElse(null);

		if (incomingTask != null) { // IM CODING: Le incoming est un task (et pas un outgoing d'un autre opérateur)
			List<Task> outgoingTasks = new ArrayList<Task>(); // IM CODING: initailiser la liste des tasks
			for (String outgoing : outgoings) {
				Task targetTask = allTasks.stream().filter(task -> task.getIncomingList().contains(outgoing))
						.findFirst().orElse(null);
				if (targetTask != null) {
					outgoingTasks.add(targetTask);
				}
			}
			// IM CODING: calculate dependecy between incoming task et outgoingTasks
			for (Task outgoingTask : outgoingTasks) {
				matrice[incomingTask.getId_matrice()][outgoingTask.getId_matrice()] = calculateDirectCostInOREvent(
						outgoingTasks.size());
				matrice[outgoingTask.getId_matrice()][incomingTask.getId_matrice()] = -2.0;

			}

			// IM CODING: calculate dependecy between outgoing tasks: On suppose pour le
			// moment que l'outgoing est toujours deux pour l'evenement OR
			// A ameliorer par ce que le nombre de branches peut dépasser 2
			if (outgoingTasks.size() == 2) {
				matrice[outgoingTasks.get(0).getId_matrice()][outgoingTasks.get(1).getId_matrice()] = -1.0;
				matrice[outgoingTasks.get(1).getId_matrice()][outgoingTasks.get(0).getId_matrice()] = -1.0;

			}

		}

		return matrice;
	}

	public static double[][] calculateDependecyFromANDEvent(ParallelGateway parrallelGatewayEvent,
			double[][] matrice) {
		// IM CODING: Trouver la liste des incoming et outgoing de l'event
		List<String> incomings = parrallelGatewayEvent.getIncomingList();
		List<String> outgoings = parrallelGatewayEvent.getOutgoingList();

		// IM CODING: On suppose que l'incoming est toujours un seul flow
		String incmoing = incomings.get(0);

		// IM CODING: Trouver le task incoming
		Task incomingTask = allTasks.stream().filter(task -> task.getOutgoingList().contains(incmoing)).findFirst()
				.orElse(null);

		if (incomingTask != null) { // IM CODING: Le incoming est un task (et pas un outgoing d'un autre opérateur)
			List<Task> outgoingTasks = new ArrayList<Task>(); // IM CODING: initailiser la liste des tasks
			for (String outgoing : outgoings) {
				Task targetTask = allTasks.stream().filter(task -> task.getIncomingList().contains(outgoing))
						.findFirst().orElse(null);
				if (targetTask != null) {
					outgoingTasks.add(targetTask);
				}
			}
			// IM CODING: calculate dependecy between incoming task et outgoingTasks
			for (Task outgoingTask : outgoingTasks) {
				matrice[incomingTask.getId_matrice()][outgoingTask.getId_matrice()] = calculateDirectCostInANDEvent(
						outgoingTasks.size());
				matrice[outgoingTask.getId_matrice()][incomingTask.getId_matrice()] = -2.0;

			}

			// IM CODING: calculate dependecy between outgoing tasks: On suppose pour le
			// moment que l'outgoing est toujours deux pour l'evenement OR
			// A ameliorer par ce que le nombre de branches peut dépasser 2
			if (outgoingTasks.size() == 2) {
				matrice[outgoingTasks.get(0).getId_matrice()][outgoingTasks.get(1).getId_matrice()] = -1.0;
				matrice[outgoingTasks.get(1).getId_matrice()][outgoingTasks.get(0).getId_matrice()] = -1.0;

			}

		}

		return matrice;
	}

	public static double[][] calculateIndirectCosts(double[][] matrice) {

		for (int i = 0; i < allTasks.size(); i++) {
			for (int j = 0; j < allTasks.size(); j++) {
				if (i != j) { // IM CODING: deux tasks differents
					if (matrice[i][j] == 0.0) { // IM CODING: le cost entre i et j n'a pas été calculé
						Task taskI = findTaskByMatriceIndex(i);
						Task taskJ = findTaskByMatriceIndex(j);

						matrice[i][j] = calculateIndirectCost(taskI, taskJ, matrice);
						System.out.println(taskI.getName() + " - " + taskJ.getName() + " : " + matrice[i][j]);
						// matrice[j][i] = matrice[i][j];
						matrice[j][i] = -2.0;
					}
				}
			}
		}

		return matrice;
	}

	public static Task findTaskByMatriceIndex(final int taskIndex) {
		return allTasks.stream().filter(task -> task.getId_matrice() == taskIndex).findFirst().orElse(null);
	}

	public static Task findTaskById(final String taskId) {
		return allTasks.stream().filter(task -> task.getId().equals(taskId)).findFirst().orElse(null);
	}

	public static Task findTaskByIncoming(final String incoming) {
		return allTasks.stream().filter(task -> task.getIncomingList().contains(incoming)).findFirst().orElse(null);
	}

	public static ExclusiveGateway findExclusiveGatewayById(final String exclusiveGatewayId) {
		return exclusiveGateWaysEvents.stream()
				.filter(exclusiveGateWay -> exclusiveGateWay.getId().equals(exclusiveGatewayId)).findFirst()
				.orElse(null);
	}

	/*
	 * public static double calculateIndirectCost(Task taskI, Task taskJ, double[][]
	 * matrice) { //IM CODING: Trouver les indexes à partir des tasks int i =
	 * taskI.getId_matrice(); int j = taskJ.getId_matrice();
	 * 
	 * // Im CODING: Si l'élement a été calculé, on retourne le cout entre i et j
	 * if(matrice[i][j] != 0.0) { return matrice[i][j]; }
	 * 
	 * // IM CODING: POUR lE MONET ON TRAVAILLE QUE AVEC XOR OU SEQUENCE
	 * 
	 * 
	 * //IM CODING: Trouver un sequence flow event avec un source ref est la task i
	 * SequenceFlow targetSequenceFlow = sequenceFlows.stream().filter(sF ->
	 * sF.getSourceRef().equals(taskI.getId())).findFirst().orElse(null);
	 * if(targetSequenceFlow != null) { // IM CODING: Trouver le target ref (l'id de
	 * task voisin de task i) String taskIdK = targetSequenceFlow.getTargetRef();
	 * 
	 * // IM CODING: Trouver le task correspondant au taskIdK Task taskK =
	 * findTaskById(taskIdK);
	 * 
	 * if(taskK != null ) { // IM CODING: le cout entre i et j sera le cout entre (i
	 * et k) * le cout entre (k et j) tel que le cout entre (i et k) est connu
	 * puisqe c'est une liaison directe déja calculée return
	 * matrice[i][taskK.getId_matrice()] * calculateIndirectCost(taskK, taskJ,
	 * matrice); } else { // le target ref est un opérateur xor String
	 * targetExclusiveGatewayId = targetSequenceFlow.getTargetRef();
	 * 
	 * //IM CODING: Trouver l'exclusive gateway correspondant à
	 * targetExclusiveGatewayId ExclusiveGateway exclusiveGateway =
	 * findExclusiveGatewayById(targetExclusiveGatewayId);
	 * 
	 * if(exclusiveGateway != null) { List<String> outGoings =
	 * exclusiveGateway.getOutgoingList(); List<Task> outGoingTasks = new
	 * ArrayList<Task>(); for(String outgoing: outGoings) { Task outGoingTask =
	 * findTaskByIncoming(outgoing); if(outGoingTask != null) {
	 * outGoingTasks.add(outGoingTask); } } //IM CODING: On a trouvé l'out going
	 * tasks à partir d'un opérateur xor from task i. Ces outgoing tasks seront les
	 * task k1 et k2 qui s'interviennenet comme des tasks intermidiares lors du
	 * calcul de cout entre i et j //On suppose que le sortie est toujours au
	 * maximum 2
	 * 
	 * double cout0 = -1; if(matrice[i][outGoingTasks.get(0).getId_matrice()] ==
	 * 0.0) { cout0 = calculateIndirectCost(taskI, outGoingTasks.get(0), matrice)*
	 * calculateIndirectCost(outGoingTasks.get(0), taskJ, matrice); } else { cout0 =
	 * matrice[i][outGoingTasks.get(0).getId_matrice()]*
	 * calculateIndirectCost(outGoingTasks.get(0), taskJ, matrice); } double cout1 =
	 * -1; // le deuxième chemin est initailisé à -1 commse s'il n'existe pas (ce
	 * valeur ne va pas influencer au resultat) if(outGoingTasks.size()>1) {
	 * if(matrice[i][outGoingTasks.get(1).getId_matrice()] == 0.0) { cout1 =
	 * calculateIndirectCost(taskI, outGoingTasks.get(1), matrice)*
	 * calculateIndirectCost(outGoingTasks.get(1), taskJ, matrice); } else { cout1 =
	 * matrice[i][outGoingTasks.get(1).getId_matrice()]*
	 * calculateIndirectCost(outGoingTasks.get(1), taskJ, matrice); } } if( cout0 <
	 * 0 && cout1 >= 0) { return cout1; } else if(cout0 >= 0 && cout1 < 0) { return
	 * cout0; } else if (cout0 < 0 && cout1 < 0) { return -1; } else { if(cout0 <
	 * cout1) return cout0; else return cout1; }
	 * 
	 * } else { return -1; // IM CODING: pas de taches suivants }
	 * 
	 * 
	 * 
	 * }
	 * 
	 * 
	 * }
	 * 
	 * // IM CODING: Trouver la liste des outgoing sortant d'un opérateur xor avec
	 * un incoming la task i //ExclusiveGateway targetExclusiveGateway =
	 * exclusiveGateWaysEvents.stream().filter(exclusiveEvent ->
	 * exclusiveEvent.getIncomingList().contains(taskI.getOutgoingList().get(0))).
	 * findFirst().orElse(null); return 0.0; }
	 */
	public static void printMatrice(double[][] matrice) {
		for (int i = 0; i < matrice.length; i++) {
			for (int j = 0; j < matrice.length; j++) {
				System.out.print(matrice[i][j] + "  |  ");
			}
			System.out.println();
			System.out.println(
					"________________________________________________________________________________________________________________________");
		}
	}

	public static double calculateDirectCostInXOREvent(int n) {
		double element = (double) 1 / n;
		return element * p;
	}
	
	public static double calculateDirectCostInANDEvent(int n) {
	
		return n * p;
	}

	public static double calculateDirectCostInOREvent(int n) {
		double somme = 0.0;
		for(int i=1; i<= n-1; i++) {
			somme = somme + i;
		}
		somme = somme + 1;
		somme = (double) somme / (n*(n-1) + 1); 
		
		return somme * p;
	}
	public static List<Integer> getAllTaskIndexesFromSourceIndex(int sourceIndex, int targetIndex, double[][] matrice) {
		List<Integer> allIndexes = new ArrayList<Integer>();
		// IM CODING: Trouver toutes les cellules dans la matrice dont i = sourceIndex
		// et leurs valeurs ont été déja calculés
		for (int j = 0; j < allTasks.size(); j++) {

			if (matrice[sourceIndex][j] != 0) {
				allIndexes.add(j);
			}

		}
		// IM CODING: Exclure toutes les index qui peuvent nous revenir en arrière
		allIndexes = allIndexes.stream().filter(index -> !excludedIndexes.contains(index)).collect(Collectors.toList());

		// IM CODING: Exclure les sucessuers qui ne peuvent pas me prendre vers le
		// target (liaison non existante)
		List<Integer> targetInts = allIndexes.stream().filter(index -> matrice[index][targetIndex] != 0.0
				&& matrice[index][targetIndex] != -1.0 && matrice[index][targetIndex] != -2.0)
				.collect(Collectors.toList());

		if (!targetInts.isEmpty()) { // IM CODEING: sucuesseurs pour calculer le target existant (90%)
			// IM CODING: Trouver le successeur avec cout maximal vers le taregt
			int indexMax = targetInts.get(0);
			for (int index : targetInts) {
				if (matrice[sourceIndex][index] * matrice[index][targetIndex] > matrice[sourceIndex][indexMax]
						* matrice[indexMax][targetIndex]) {
					indexMax = index;
				}
			}

			List<Integer> indexes = new ArrayList<>();
			indexes.add(indexMax);
			return indexes;// IM CODING: retourner une liste avec un seul valeur
		} else {
			return allIndexes;
		}

	}

	// Chercher une autre aletrnative pour les processus independant
	public static double[][] calculateAnotherPath(double[][] matrice) {
		for (int i = 0; i < allTasks.size(); i++) {
			for (int j = 0; j < allTasks.size(); j++) {
				if (matrice[i][j] == -1.0) {
					List<Integer> nextIndexes = new ArrayList<Integer>();
					for (int k = 0; k < allTasks.size(); k++) {

						if (matrice[i][k] != -1.0 && matrice[i][k] != -2.0 && matrice[k][j] != -1.0
								&& matrice[k][j] != -2.0) {
							nextIndexes.add(k);
						}

					}
					if (!nextIndexes.isEmpty()) {

						int indexMax = nextIndexes.get(0);
						for (int index : nextIndexes) {
							if (matrice[i][index] * matrice[index][j] > matrice[i][indexMax] * matrice[indexMax][j]) {
								indexMax = index;
							}
						}

						matrice[i][j] = matrice[i][indexMax] * matrice[indexMax][j];
					} else {
						List<Integer> nextIndexes2 = new ArrayList<Integer>();
						for (int k = 0; k < allTasks.size(); k++) {

							if (matrice[j][k] != -1.0 && matrice[j][k] != -2.0 && matrice[k][i] != -1.0
									&& matrice[k][i] != -2.0) {
								nextIndexes2.add(k);
							}

						}
						if (!nextIndexes2.isEmpty()) {

							int indexMax = nextIndexes2.get(0);
							for (int index : nextIndexes2) {
								if (matrice[j][index] * matrice[index][i] > matrice[j][indexMax]
										* matrice[indexMax][i]) {
									indexMax = index;
								}
							}

							matrice[i][j] = matrice[j][indexMax] * matrice[indexMax][i];
						}

					}
				}

			}
		}
		return matrice;
	}

	public static double getMinCosts(List<Double> costs) {
		costs = costs.stream().filter(cost -> cost > 0.0).collect(Collectors.toList());
		if (costs.size() > 0) {
			return costs.stream().map(cost -> cost).mapToDouble(cost -> cost).max().getAsDouble();
		} else {
			return -1;
		}

	}

	public static double calculateIndirectCost(Task taskI, Task taskJ, double[][] matrice) {
		// IM CODING: si on est à un noued final
		/*
		 * if(taskI.getOutgoingList() == null || taskI.getOutgoingList().isEmpty()) {
		 * return -1; }
		 */
		// IM CODING: Trouver les indexes à partir des tasks
		int i = taskI.getId_matrice();
		excludedIndexes.add(i);
		int j = taskJ.getId_matrice();

		// Im CODING: Si l'élement a été calculé, on retourne le cout entre i et j
		if (matrice[i][j] != 0.0) {
			excludedIndexes.clear();
			return matrice[i][j];
		}

		List<Integer> sucessiveTasks = getAllTaskIndexesFromSourceIndex(i, j, matrice);
		if (sucessiveTasks.size() == 0) {
			excludedIndexes.clear();
			return -1;
		} else {
			List<Double> costs = new ArrayList<>();

			Task taskK = findTaskByMatriceIndex(sucessiveTasks.get(0));
			costs.add(matrice[i][sucessiveTasks.get(0)] * calculateIndirectCost(taskK, taskJ, matrice));
			return getMinCosts(costs);

			// return matrice[i][sucessiveTasks.get(0)] * calculateIndirectCost(taskK,
			// taskJ, matrice);

		}

		// IM CODING: Trouver la liste des outgoing sortant d'un opérateur xor avec un
		// incoming la task i
		// ExclusiveGateway targetExclusiveGateway =
		// exclusiveGateWaysEvents.stream().filter(exclusiveEvent ->
		// exclusiveEvent.getIncomingList().contains(taskI.getOutgoingList().get(0))).findFirst().orElse(null);

	}
}
