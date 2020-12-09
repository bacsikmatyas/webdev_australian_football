package hu.unideb.webdev.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/***
 * MatchStats
 *
 * @Column(name = "loc")
 *     private String location;
 *
 *     @Column(name = "ki")
 *     private Integer kicks;
 *
 *     @Column(name = "mk")
 *     private Integer marks;
 *
 *     @Column(name = "hb")
 *     private Integer handballs;
 *
 *     @Column(name = "di")
 *     private Integer disposals;
 *
 *     @Column(name = "gl")
 *     private Integer goals;
 *
 *     @Column(name = "bh")
 *     private Integer behinds;
 *
 *     @Column(name = "ho")
 *     private Integer hitOuts;
 *
 *     @Column(name = "tk")
 *     private Integer tackles;
 *
 *     @Column(name = "rb")
 *     private Integer rebound50s;
 *
 *     @Column(name = "if")
 *     private Integer inside50s;
 *
 *     @Column(name = "cl")
 *     private Integer clearances;
 *
 *     @Column(name = "cg")
 *     private Integer clangers;
 *
 *     @Column(name = "ff")
 *     private Integer freeKicksFor;
 *
 *     @Column(name = "fa")
 *     private Integer freeKicksAgainst;
 *
 *     @Column(name = "br")
 *     private Integer brownlowVotes;
 *
 *     @Column(name = "cp")
 *     private Integer contestedPossessions;
 *
 *     @Column(name = "up")
 *     private Integer uncontestedPossessions;
 *
 *     @Column(name = "cm")
 *     private Integer contestedMarks;
 *
 *     @Column(name = "mi")
 *     private Integer marksInside50;
 *
 *     @Column(name = "1p")
 *     private Float onePercenters;
 *
 *     @Column(name = "bo")
 *     private Integer bounces;
 *
 *     @Column(name = "ga")
 *     private Integer goalAssist;
 *
 *     @Column(name = "pp")
 *     private Float percentageOfGamePlayed;
 */
@AllArgsConstructor
@ToString
@Getter
@EqualsAndHashCode
public class MatchStats {

    private String mid;
    private int pid;
    private int tid;
    private String loc;
    private Integer KI;
    private Integer MK;
    private Integer HB;
    private Integer DI;
    private Integer GL;
    private Integer BH;
    private Integer HO;
    private Integer TK;
    private Integer RB;
    private Integer IF;
    private Integer CL;
    private Integer CG;
    private Integer FF;
    private Integer FA;
    private Integer BR;
    private Integer CP;
    private Integer UP;
    private Integer CM;
    private Integer MI;
    private Float oneP;
    private Integer BO;
    private Integer GA;
    private Float PP;

}
