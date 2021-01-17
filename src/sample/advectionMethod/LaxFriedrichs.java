package sample.advectionMethod;

import sample.advectionMethod.base.AdvectionAlgorithm;

public class LaxFriedrichs extends AdvectionAlgorithm {

    public LaxFriedrichs(int numberOfNodes, double maxT, double maxX, double minX, double dt, double v, double xc) {
        super(numberOfNodes, maxT, maxX, minX, dt, v, xc);
    }

    @Override
    public String getMethodName()
    {
        return "Lax-Friedrichs";
    }

    @Override
    public void advectionMethod()
    {
        chartYDataTmp[0] = chartYData[0] - alpha * (chartYData[1] - chartYData[chartYData.length-1])
                + 0.5 * (chartYData[1] - 2 * chartYData[0] + chartYData[chartYData.length-1]);

        for (int i = 1; i< chartYData.length-1 ; i++)
            chartYDataTmp[i] = chartYData[i] - alpha * (chartYData[i + 1] - chartYData[i - 1])
                    + 0.5 * (chartYData[i + 1] - 2 * chartYData[i] + chartYData[i - 1]);

        chartYData = chartYDataTmp.clone();
        /*
        Periodic boundary conditions
         */
        chartYData[0] = chartYData[chartYData.length-2];
        chartYData[chartYData.length-1] = chartYData[1];
    }
}

