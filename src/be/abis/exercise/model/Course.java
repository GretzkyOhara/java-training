package be.abis.exercise.model;


import java.time.LocalDate;
import java.util.Comparator;

public class Course implements Comparable <Course> {
	private String title;
	private int days;
	private double dailyPrice;
	private LocalDate releaseDate;


	public Course() {
	}

	public Course(String title, int days, double dailyPrice, LocalDate releaseDate) {
		this.title = title;
		this.days = days;
		this.dailyPrice = dailyPrice;
		this.releaseDate=releaseDate;
	}

	public String getTitle() {
		return title;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public double getDailyPrice() {
		return dailyPrice;
	}

	public void setDailyPrice(double dailyPrice) {
		this.dailyPrice = dailyPrice;
	}


	public LocalDate getReleaseDate() {
		return releaseDate;
	}


	public double calculateTotalPrice() {

		double theResult=this.getDays()*this.getDailyPrice();

		return theResult;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	@Override
	public String toString() {
		return "Course{" +
				"title='" + title + '\'' +
				", days=" + days +
				", dailyPrice=" + dailyPrice +
				", releaseDate=" + releaseDate +
				'}';
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public int compareTo(Course c) {

		return this.title.compareTo(c.getTitle());

	}




public static class CourseByDaysComparator implements Comparator<Course> {


	@Override
	public int compare(Course c1, Course c2) {

		return c1.getDays() - c2.getDays();

	}
}

	public static class CourseByDailyPriceComparator implements Comparator<Course> {


		@Override
		public int compare(Course c1, Course c2) {

			if (c1.getDailyPrice() < c2.getDailyPrice())
				return -1;

			else if (c1.getDailyPrice() == c2.getDailyPrice())
				return 0;

			else
				return 1;

		}
	}
}