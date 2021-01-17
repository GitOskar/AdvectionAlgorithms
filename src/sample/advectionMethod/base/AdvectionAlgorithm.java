package sample.advectionMethod.base;

public abstract class AdvectionAlgorithm
{
    protected int stepsNumber;
    protected double deltaTime, velocity, xc, deltaX, alpha, tc;
    protected double[] chartXData, chartYData, chartYDataTmp;

    public AdvectionAlgorithm(int numberOfNodes, double maxT, double maxX, double minX, double deltaTime, double velocity, double xc)
    {
        this.deltaTime = deltaTime;
        this.velocity = velocity;
        this.xc = xc;
        this.deltaX = (maxX - minX) / numberOfNodes;
        arange(minX, maxX);
        initializeChartYData();
        initializeParams(maxT);
        tc = 0;
    }

    private void arange(double minX, double maxX) {

        double begin = minX - deltaX;
        double end = maxX + 2 * deltaX;
        int size = (int) ((end - begin) / deltaX);
        this.chartXData = new double[size];

        double var = begin;
        for (int i = 0; i < size; i++) {
            chartXData[i] = var;
            var += deltaX;
        }
    }

    private void initializeChartYData() {
        chartYData = gaussianCurveForAllElements(chartXData);
        chartYDataTmp = chartYData.clone();
    }

    private void initializeParams(double maxT)
    {
        stepsNumber = (int)Math.round(maxT/ deltaTime);
        alpha = velocity * deltaTime /(2* deltaX);
    }

    private double[] gaussianCurveForAllElements(double[] array)
    {
        double[] result = new double[array.length];

        for (int i=0 ; i<array.length ; i++) {
            result[i] = gaussianCurve(array[i]);
        }

        return result;
    }

    private double[] gaussianCurveForAllElements(double[] array, double tc)
    {
        double[] result = new double[array.length];

        if (tc > 1-xc)
            tc -= 1.01;

        for (int i=0 ; i<array.length ; i++) {
            result[i] = gaussianCurve(array[i], tc);
        }

        result[0] = gaussianCurve(result[result.length-2]);
        result[result.length-1] = result[1];
        return result;
    }

    private double gaussianCurve(double value)
    {
        return Math.exp(-200 * Math.pow(value-xc, 2.0));
    }

    private double gaussianCurve(double value, double tc)
    {
        return Math.exp(-200 * Math.pow(value-xc-velocity * tc, 2.0));
    }

    abstract public String getMethodName();

    abstract public void advectionMethod();

    public int getStepsNumber()
    {
        return stepsNumber;
    }

    public double[] getChartXData() {
        return chartXData;
    }

    public double[] getChartYData() {
        return chartYData;
    }

    public double[] getChartExactYData(){
        return gaussianCurveForAllElements(chartXData, tc+=deltaTime);
    }

    public double getTc()
    {
        return tc;
    }
}
