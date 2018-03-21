package fr.elfoa.drone;

import javax.inject.Inject;

/**
 * @author Pierre Colomb
 */
public class Point {

    private Double latitude;

    private Double longitude;

    private Double altitude;

    @Inject
    public Point() {
        this.latitude = 0.0d;
        this.longitude = 0.0d;
        this.altitude = 0.0d;
    }

    public Point(Double latitude, Double longitude, Double altitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
    }



    public Double getAltitude() {
        return altitude;
    }


    public Double getLongitude() {
        return longitude;
    }


    public Double getLatitude() {
        return latitude;
    }

    public Double distanceTo(Point point){

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(point.latitude - latitude);

        double lonDistance = Math.toRadians(point.longitude - longitude);

        double a = Math.sin(latDistance / 2) *
                   Math.sin(latDistance / 2) +
                   Math.cos(Math.toRadians(latitude)) *
                   Math.cos(Math.toRadians(point.latitude)) *
                   Math.sin(lonDistance / 2) *
                   Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double distance = R * c * 1000; // convert to meters

        double height = altitude - point.altitude;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Point)) {
            return false;
        }

        Point p = (Point)o;
        return (this.latitude.equals(p.latitude) &&
                this.longitude.equals(p.longitude) &&
                this.altitude.equals(p.altitude));
    }
}
