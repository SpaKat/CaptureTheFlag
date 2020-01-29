package Gui_old;

public class ColorHexConveter {


	private int hex;

	public ColorHexConveter(int hex) {
		this.hex = hex;
	}

	@Override
	public String toString() {
		return String.format("#%06X", hex).toUpperCase();
	}
}
