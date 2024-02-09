package dao;

import java.util.List;

import model.Subject;

public interface SubjectDao {

	public void create(Subject s);
	public List<Subject> read();
	public void update(Subject s);
	public void delete(Integer id);
}
