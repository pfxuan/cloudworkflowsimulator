package cws.core.experiment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cws.core.algorithms.*;
import cws.core.cloudsim.CloudSimWrapper;
import cws.core.dag.DAG;
import cws.core.dag.DAGParser;
import cws.core.dag.Task;
import cws.core.jobs.UniformRuntimeDistribution;

/**
 * Factory for creating algorithms based on experiment description.
 * @author malawski
 */
public class AlgorithmFactory {

    public static Algorithm createAlgorithm(ExperimentDescription e, CloudSimWrapper cloudsim,
            AlgorithmSimulationParams simulationParams) {
        List<DAG> dags = new ArrayList<DAG>();
        String name = e.getAlgorithmName();
        String dagPath = e.getDagPath();
        for (String dagName : e.getDags()) {
            DAG dag = DAGParser.parseDAG(new File(dagPath + "/" + dagName));
            dags.add(dag);

            // scale tasks size
            double dilatationFactor = e.getTaskDilatation();
            for (String tid : dag.getTasks()) {
                Task t = dag.getTaskById(tid);
                t.scaleSize(dilatationFactor);
            }
        }

        int runId = e.getRunID();

        double runtimeVariation = e.getRuntimeVariation();
        if (runtimeVariation > 0.0) {
            VMFactory.setRuntimeDistribution(new UniformRuntimeDistribution(runId, runtimeVariation));
        }

        double delay = e.getDelay();
        if (delay > 0.0) {
            VMFactory.setProvisioningDelayDistribution(new ConstantDistribution(delay));
        }

        double budget = e.getBudget();
        double deadline = e.getDeadline();
        double alpha = e.getAlpha();
        double price = e.getPrice();
        double maxScaling = e.getMax_scaling();

        if (name.equals("MaxMin"))
            return new MaxMin(budget, deadline, dags, cloudsim, simulationParams);
        else if (name.equals("Wide"))
            return new Wide(budget, deadline, dags, cloudsim, simulationParams);
        else if (name.equals("Backtrack"))
            return new Backtrack(budget, deadline, dags, cloudsim, simulationParams);
        else if (name.equals("SPSS"))
            return new SPSS(budget, deadline, dags, alpha, cloudsim, simulationParams);
        else if (name.equals("DPDS"))
            return new DPDS(budget, deadline, dags, price, maxScaling, cloudsim, simulationParams);
        else if (name.equals("WADPDS"))
            return new WADPDS(budget, deadline, dags, price, maxScaling, cloudsim, simulationParams);
        else
            return null;

    }
}
