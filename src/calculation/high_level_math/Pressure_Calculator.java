package calculation.high_level_math;

import math.math_primitives.Operator;
import math.math_primitives.Panel;
import math.math_primitives.Vector;

public class Pressure_Calculator {

    Operator o = Operator.getInstance();

    public double calculate_pressure(Panel panel, double inner_pressure, double inner_density, Vector V_inf){
        Vector flow_velocity = panel.flow_velocity;

        return inner_pressure + inner_density/2 * (o.scalar_mul(V_inf,V_inf) - o.scalar_mul(flow_velocity,flow_velocity));
    }

    public double calculate_dimless_pressure(Panel panel, Vector V_inf){
        Vector flow_velocity = panel.flow_velocity;

        return 1 - o.scalar_mul(flow_velocity,flow_velocity)/o.scalar_mul(V_inf,V_inf);
    }


}
