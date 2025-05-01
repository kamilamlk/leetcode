package com.leetcode.ocp.enums;

public class MachineClient {
    public static void main(String[] args) {
        Machine machine = new Machine();
        machine.setState(MachineState.BUSY);

    }
}
class Machine {
    private MachineState state;

    public MachineState getState() {
        return state;
    }

    public void setState(MachineState state) {
        this.state = state;
    }
}

enum MachineState {
    BUSY, IDLE, BLOCKED;

}