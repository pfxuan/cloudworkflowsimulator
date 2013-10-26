package cws.core.experiment.generators;

import cws.core.experiment.DAGListGenerator;

/**
 * Tests series of ensembles consisting of workflows form workflow generator
 * https://confluence.pegasus.isi.edu/display/pegasus/WorkflowGenerator
 * 
 * From each workflow type DAGs of sizes 1000 selected.
 * 
 * @author malawski
 */
public class GenerateExperimentConstant extends AbstractGenerateExperiment {
    public static void main(String[] args) {
        new GenerateExperimentConstant().generate(args);
    }

    @Override
    public void doGenerate() {
        maxScaling = 0.0;
        group = "constant";
        runDirectory = "run-02-constant";
        runtimeVariation = 0.0;
        distribution = "constant";
        dagPath = dagPathPrefix + "Montage/";
        dagName = "MONTAGE";

        String[] dags = DAGListGenerator.generateDAGListConstant(dagName, 1000, 100);
        maxHours = 20;
        stepHours = 1;
        startHours = 1;
        maxScaling = 0;

        // double[] budgets= {10.0, 20.0, 30.0, 40.0, 50.0, 60.0, 70.0, 80.0, 90.0, 100.0};
        double[] budgets = { 40.0, 80.0, 120.0, 160.0, 200.0 };

        generateSeries(budgets, dags);

        dagPath = dagPathPrefix + "CyberShake/";
        dagName = "CYBERSHAKE";

        dags = DAGListGenerator.generateDAGListConstant(dagName, 1000, 100);

        maxHours = 20;
        stepHours = 1;
        startHours = 1;
        maxScaling = 0;

        // budgets = new double[] {10.0, 20.0, 30.0, 40.0, 50.0, 60.0, 80.0, 100.0, 120.0, 140.0};
        budgets = new double[] { 50.0, 150.0, 250.0, 350.0, 450.0 };

        generateSeries(budgets, dags);

        dagPath = dagPathPrefix + "LIGO/";
        dagName = "LIGO";

        dags = DAGListGenerator.generateDAGListConstant(dagName, 1000, 100);

        maxHours = 40;
        stepHours = 1;
        startHours = 1;
        maxScaling = 0;

        // budgets = new double[] {200.0, 400.0, 600.0, 800.0, 1000.0, 1200.0, 1400.0, 1600.0, 1800.0, 2000.0};
        budgets = new double[] { 500.0, 1000.0, 1500.0, 2000.0, 2500.0 };

        generateSeries(budgets, dags);

        dagPath = dagPathPrefix + "Genome/";
        dagName = "GENOME";

        dags = DAGListGenerator.generateDAGListConstant(dagName, 1000, 100);

        maxHours = 1500;
        stepHours = 100;
        startHours = 100;
        maxScaling = 0;

        // budgets = new double[] {2000.0, 4000.0, 6000.0, 8000.0, 10000.0, 12000.0, 14000.0, 16000.0, 18000.0,
        // 20000.0};
        budgets = new double[] { 5000.0, 10000.0, 15000.0, 20000.0, 25000.0 };

        generateSeries(budgets, dags);

        dagPath = dagPathPrefix + "SIPHT/";
        dagName = "SIPHT";

        dags = DAGListGenerator.generateDAGListConstant(dagName, 1000, 100);

        maxHours = 50;
        stepHours = 5;
        startHours = 5;
        maxScaling = 0;

        // double[] budgets= {200.0, 300.0, 400.0, 500.0, 600.0, 700.0, 800.0, 900.0, 1000.0, 1100.0};
        budgets = new double[] { 500.0, 1000.0, 1500.0, 2000.0, 2500.0 };

        generateSeries(budgets, dags);
    }
}