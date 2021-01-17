package sample.advectionMethod;

import sample.advectionMethod.base.AdvectionAlgorithm;

public class BeamWarming extends AdvectionAlgorithm {

    private double alpha2;

    public BeamWarming(int numberOfNodes, double maxT, double maxX, double minX, double deltaTime, double velocity, double xc) {
        super(numberOfNodes, maxT, maxX, minX, deltaTime, velocity, xc);
        alpha2 = velocity * velocity * deltaTime * deltaTime / (2 * deltaX * deltaX);
    }

    @Override
    public String getMethodName() {
        return "Beam-Warming";
    }

    @Override
    public void advectionMethod() {

        chartYDataTmp[0] = chartYData[0] - alpha * (3*chartYData[0] - 4*chartYData[chartYData.length-1] + chartYData[chartYData.length-2])
                + alpha2 * (chartYData[0] - 2*chartYData[chartYData.length-1] + chartYData[chartYData.length-2]);

        chartYDataTmp[1] = chartYData[1] - alpha * (3*chartYData[1] - 4*chartYData[0] + chartYData[chartYData.length-1])
                + alpha2 * (chartYData[1] - 2*chartYData[0] + chartYData[chartYData.length-1]);

        for (int i = 2; i< chartYData.length-1 ; i++)
            chartYDataTmp[i] = chartYData[i] - alpha * (3*chartYData[i] - 4*chartYData[i-1] + chartYData[i-2])
                    + alpha2 * (chartYData[i] - 2*chartYData[i-1] + chartYData[i-2]);

        chartYData = chartYDataTmp.clone();
        /*
        Periodic boundary conditions
         */
        chartYData[0] = chartYData[chartYData.length-2];
        chartYData[chartYData.length-1] = chartYData[1];
    }
}
