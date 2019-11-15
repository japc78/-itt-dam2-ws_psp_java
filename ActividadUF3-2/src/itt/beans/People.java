package itt.beans;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="members")
public class People {
	private ArrayList<Person> group;

	public People() {
		group = new ArrayList<Person>();
	}

	@XmlElement(name = "person")
	public ArrayList<Person> getGroup() {
		return group;
	}

	public void setGroup(ArrayList<Person> group) {
		this.group = group;
	}

}
