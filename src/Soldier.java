

public class Soldier {
   protected String name;
   protected int cost;
   protected int attack_power;

	public Soldier(String name,int cost,int attack_power) {
		this.name=name;
		this.cost=cost;
		this.attack_power=attack_power;
	}

	public void unitInfo() {
		System.out.println(name + " " + this.getClass().getSimpleName()+" "+ cost + " " + attack_power);
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int value) {
		this.cost = value;
	}

	public int getAttackPoint() {
		return attack_power;
	}

	public void setAttackPoint(int combat_power) {
		this.attack_power = combat_power;
	}
	
	
}
