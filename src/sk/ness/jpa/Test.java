package sk.ness.jpa;
import javax.persistence.EntityManager;

public class Test {

	public static void main(String[] args) throws Exception {
		// Score score = new Score("Minesweeper","janko",100);
		// score.setPlayer("janko");
		// score.setGame("mines");
		// score.setPoints(100);
		//
		// JpaHelper.beginTransaction();
		// JpaHelper.getEntityManager().persist(score);
		// JpaHelper.commitTransaction();
		// JpaHelper.closeAll();

		JpaHelper.beginTransaction();
		// EntityManager m = JpaHelper.getEntityManager();//.persist(score);
		Student student = new Student();
		student.setMeno("janko");
		student.setPriezvisko("hrasko");
		student.setVek(20);
		// m.persist(student);
		// JpaHelper.commitTransaction();
		EntityManager m = JpaHelper.getEntityManager();// .persist(score);
		// System.out.print(m.createQuery("select s from Student
		// s").getResultList());
		javax.persistence.Query query = m.createQuery("select s from Student s where s.meno=:meno ");
		query.setParameter("meno", "janko");
		System.out.println(query.getResultList());
		// m.remove(student);
		// student = m.find(Student.class, 2);
		// student.setVek(26);

		JpaHelper.commitTransaction();
		JpaHelper.closeAll();

		// syntax JPQL
	}

}