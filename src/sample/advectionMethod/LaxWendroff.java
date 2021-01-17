package sample.advectionMethod;

import sample.advectionMethod.base.AdvectionAlgorithm;

public class LaxWendroff extends AdvectionAlgorithm
{
    public LaxWendroff(int numberOfNodes, double maxT, double maxX, double minX, double deltaTime, double velocity, double xc) {
        super(numberOfNodes, maxT, maxX, minX, deltaTime, velocity, xc);
    }

    @Override
    public String getMethodName() {
        return "Lax-Wendroff";
    }

    @Override
    public void advectionMethod() {

        chartYDataTmp[0] = chartYData[0] +
                (velocity*velocity * deltaTime * deltaTime / (2*deltaX*deltaX))
                *(chartYData[1] - 2*chartYData[0]+chartYData[chartYData.length-1])
                - alpha*(chartYData[1]-chartYData[chartYData.length-1]);

        for (int i = 1; i< chartYData.length-1 ; i++)
            chartYDataTmp[i] =
                    chartYData[i] + (velocity*velocity * deltaTime * deltaTime / (2*deltaX*deltaX)) *(chartYData[i+1]
                    - 2*chartYData[i]+chartYData[i-1]) - alpha*(chartYData[i+1]-chartYData[i-1]);

        chartYData = chartYDataTmp.clone();
        /*
        Periodic boundary conditions
         */
        chartYData[0] = chartYData[chartYData.length-2];
        chartYData[chartYData.length-1] = chartYData[1];
    }
}
