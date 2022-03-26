/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

/**
 *
 * @author bartk
 */
@Named(value = "newJSFManagedBean")
@RequestScoped
public class NewJSFManagedBean {

    public class Student {

        private String name;
        private String surname;
        private Double avg;

        /**
         * @return the name
         */
        public String getName() {
            return name;
        }

        /**
         * @param name the name to set
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * @return the surname
         */
        public String getSurname() {
            return surname;
        }

        /**
         * @param surname the surname to set
         */
        public void setSurname(String surname) {
            this.surname = surname;
        }

        /**
         * @return the avg
         */
        public Double getAvg() {
            return avg;
        }

        /**
         * @param avg the avg to set
         */
        public void setAvg(Double avg) {
            this.avg = avg;
        }

    }

    private Integer number1;
    private Integer number2;
    private Integer result;
    private Date currentDate = new Date();
    private List<Student> students;

    private LineChartModel lineModel;

    @PostConstruct
    public void init() {
        students = new ArrayList<>();

        Student s1 = new Student();
        s1.name = "Jan";
        s1.surname = "Nowak";
        s1.avg = 4.56;

        Student s2 = new Student();
        s2.name = "Adas";
        s2.surname = "Wisniewski";
        s2.avg = 4.48;

        Student s3 = new Student();
        s3.name = "Michal";
        s3.surname = "Szpak";
        s3.avg = 3.21;

        students.add(s1);
        students.add(s2);
        students.add(s3);

        createLineModel();
    }

    public void createLineModel() {
        lineModel = new LineChartModel();

        LineChartSeries sin = new LineChartSeries();
        sin.setLabel("Sin");
        for (int i = 0; i <= 360; i += 10) {
            sin.set(i, sin(Math.toRadians(i)));
        }
        lineModel.addSeries(sin);

        LineChartSeries cos = new LineChartSeries();
        cos.setLabel("Cos");
        for (int i = 0; i <= 360; i += 10) {
            cos.set(i, cos(Math.toRadians(i)));
        }
        lineModel.addSeries(cos);

        lineModel.setLegendPosition("e");
        lineModel.setZoom(true);

        Axis x = lineModel.getAxis(AxisType.X);
        x.setMin(0);
        x.setMax(360);
        x.setTickInterval("10");
        x.setLabel("Degree");

        Axis y = lineModel.getAxis(AxisType.Y);
        y.setMin(-1);
        y.setMax(1);
        y.setLabel("Value");
    }

    /**
     * Creates a new instance of NewJSFManagedBean
     */
    public NewJSFManagedBean() {
    }

    /**
     * @return the number1
     */
    public Integer getNumber1() {
        return number1;
    }

    /**
     * @param number1 the number1 to set
     */
    public void setNumber1(Integer number1) {
        this.number1 = number1;
    }

    /**
     * @return the number2
     */
    public Integer getNumber2() {
        return number2;
    }

    /**
     * @param number2 the number2 to set
     */
    public void setNumber2(Integer number2) {
        this.number2 = number2;
    }

    public void calculate() {
        setResult(getNumber1() + getNumber2());
        String message = number1.toString() + " + " + number2.toString() + " = " + result.toString();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
    }

    /**
     * @return the result
     */
    public Integer getResult() {
        return result;
    }

    /**
     * @param result the result to set
     */
    public void setResult(Integer result) {
        this.result = result;
    }

    /**
     * @return the current_date
     */
    public Date getCurrentDate() {
        return currentDate;
    }

    /**
     * @param current_date the current_date to set
     */
    public void setCurrentDate(Date current_date) {
        this.currentDate = current_date;
    }

    /**
     * @return the students
     */
    public List<Student> getStudents() {
        return students;
    }

    /**
     * @param students the students to set
     */
    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public LineChartModel getLineModel() {
        return lineModel;
    }

    /**
     * @param lineModel the lineModel to set
     */
    public void setLineModel(LineChartModel lineModel) {
        this.lineModel = lineModel;
    }

}
