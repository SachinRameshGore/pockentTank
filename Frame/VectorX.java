

public class VectorX {
		double x;
		double y;
		double angle;
		double length;
		public VectorX(double x, double y) {
			this.x = x;
			this.y = y;
			this.length = Math.sqrt(this.x*this.x + this.y*this.y);
			angle = Math.atan2(y,x);
		}
		public VectorX(VectorX vect){
			this.x = vect.x;
			this.y = vect.y;
			this.length = vect.length;
			this.angle = vect.angle;
		}
		public void addTo(VectorX v){
			VectorX temp;
			double tempX,tempY;
			this.x = this.x + v.x;
			this.y = this.y + v.y;
			update();
			
		}
		public void subtractFrom(VectorX v){
			VectorX temp;
			double tempX,tempY;
			this.x = this.x - v.x;
			this.y = this.y - v.y;
			update();
		}
		public void update(){
			this.length = Math.sqrt(this.x*this.x + this.y*this.y);
			double ang = Math.atan2(y,x);
		}
		public void update2(){
			this.x = length * Math.cos(angle);
			this.y = length * Math.sin(angle);
		}
		public void setByAngleLength(double angle,double length){
			this.angle = angle;
			this.length = length;
			update2();
		}
		public double getX() {
			return x;
		}
		public void setX(double x) {
			this.x = x;
		}
		public double getY() {
			return y;
		}
		public void setY(double y) {
			this.y = y;
		}
		public double getAngle() {
			return angle;
		}
		public void setAngle(double angle) {
			this.angle = angle;
			update();
		}
		public void multiplyBY(VectorX v){
			this.x *= v.x;
			this.y *= v.y;
			update();
		}
		public double getLength() {
			return length;
		}
		public void setLength(double length) {
			this.length = length;
			update();
		}
		
}
