package com.example.jeepseekapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "routes")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private String name;
    @Column(name = "from_loc")
    private String from;
    @Column(name = "to_loc")
    private String to;
    @Column(length = 500)
    private String via;
    private String fare;
    private String color;

    /** GeoJSON-style "[[lat,lng],[lat,lng],...]" stored as text for now. */
    @Column(length = 4000)
    private String pathJson;

    public Route() {}

    public Route(String code, String name, String from, String to, String via,
                 String fare, String color, String pathJson) {
        this.code = code; this.name = name; this.from = from; this.to = to;
        this.via = via; this.fare = fare; this.color = color; this.pathJson = pathJson;
    }

    public Long getId() { return id; }
    public String getCode() { return code; }
    public String getName() { return name; }
    public String getFrom() { return from; }
    public String getTo() { return to; }
    public String getVia() { return via; }
    public String getFare() { return fare; }
    public String getColor() { return color; }
    public String getPathJson() { return pathJson; }

    public void setId(Long id) { this.id = id; }
    public void setCode(String code) { this.code = code; }
    public void setName(String name) { this.name = name; }
    public void setFrom(String from) { this.from = from; }
    public void setTo(String to) { this.to = to; }
    public void setVia(String via) { this.via = via; }
    public void setFare(String fare) { this.fare = fare; }
    public void setColor(String color) { this.color = color; }
    public void setPathJson(String pathJson) { this.pathJson = pathJson; }
}