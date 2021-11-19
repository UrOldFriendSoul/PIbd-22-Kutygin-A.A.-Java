public enum RadarAmount {
    One,
    Two,
    Three;

    public static RadarAmount getRadarAmount(int n){

        switch (n){
            case 1:
                return RadarAmount.One;
            case 2:
                return RadarAmount.Two;
            case 3:
                return RadarAmount.Three;
        }
        return null;
    }
}
