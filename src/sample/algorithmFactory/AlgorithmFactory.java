package sample.algorithmFactory;

import sample.advectionMethod.BeamWarming;
import sample.advectionMethod.LaxFriedrichs;
import sample.advectionMethod.LaxWendroff;
import sample.advectionMethod.advectionAlgorithmEnum.AdvectionAlgorithmEnum;
import sample.advectionMethod.base.AdvectionAlgorithm;

public class AlgorithmFactory
{
    public AdvectionAlgorithm createAlgorithm(AdvectionAlgorithmEnum advectionAlgorithmEnum)
    {
        switch (advectionAlgorithmEnum)
        {
            case LAX_FRIEDRICHS: return new LaxFriedrichs(100, 1.5, 1.0, 0, 0.009, 1.0, 0.25);
            case LAX_WENDROFF: return new LaxWendroff(100, 1.5, 1.0, 0, 0.009, 1.0, 0.25);
            case BEAM_WARMING: return new BeamWarming(100, 1.5, 1.0, 0, 0.009, 1.0, 0.25);
            default: throw new NullPointerException("Algorithm not found");
        }
    }
}
