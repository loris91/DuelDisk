package com.example.dueldisk;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	EditText usernameSX = null;
	TextView lifePointsSX = null;
	EditText usernameDX = null;
	TextView lifePointsDX = null;
	TextView damage = null;
	ProgressBar progressBarDX = null;
	ProgressBar progressBarSX = null;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		this.usernameSX = (EditText) findViewById(R.id.editUsernameSX);
		this.lifePointsSX = (TextView) findViewById(R.id.textLifepointsSX);
		this.usernameDX = (EditText) findViewById(R.id.editUsernameDX);
		this.lifePointsDX = (TextView) findViewById(R.id.textLifepointsDX);
		this.damage = (TextView) findViewById(R.id.textDamage);
		this.progressBarSX = (ProgressBar) findViewById(R.id.progressBarSX);
		this.progressBarDX = (ProgressBar) findViewById(R.id.progressBarDX);
		
		String nameSX = this.usernameSX.getText().toString();
		String nameDX = this.usernameDX.getText().toString();
		
		final Toast toastReset = Toast.makeText(this, "All data have been reset!", Toast.LENGTH_SHORT);
		
		// Reset button
		final Button resetButton = (Button) findViewById(R.id.buttonReset);
		resetButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				playSound("click_sound.mp3");
				usernameDX.setText("");
				usernameSX.setText("");
				damage.setText("0");
				lifePointsDX.setText("8000");
				lifePointsSX.setText("8000");
				toastReset.show();
			}
		});

		// Cancel button
		final Button cancelButton = (Button) findViewById(R.id.buttonCancel);
		cancelButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				playSound("click_sound.mp3");
				String damageValue = (String) damage.getText();

				if (damageValue.length() == 1) {
					damage.setText("0");
				} else {
					damageValue = damageValue.substring(0, damageValue.length() - 1);
					damage.setText(damageValue);
				}
			}
		});

		// Dice random button
		final Button buttonDice = (Button) findViewById(R.id.buttonDice);
		buttonDice.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				int diceValue = (int) (Math.random() * 10) % 6;
				diceValue++;
				buttonDice.setText("Dice: " + diceValue);
				playSound("rolling_dice.mp3");
			}
		});

		// Coin random button
		final Button buttonCoin = (Button) findViewById(R.id.buttonCoin);
		buttonCoin.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				playSound("rolling_coin.mp3");
				int coinValue = (int) (Math.random() * 10);
				if (coinValue % 2 == 0) {
					buttonCoin.setText("Coin: Head");
				} else {
					buttonCoin.setText("Coin: Tail");
				}
			}
		});

		// Minus SX button
		final Button buttonMinusSX = (Button) findViewById(R.id.buttonMinusSX);
		buttonMinusSX.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				changeLifepoints(lifePointsSX, "-");
			}
		});

		// Minus DX button
		final Button buttonMinusDX = (Button) findViewById(R.id.buttonMinusDX);
		buttonMinusDX.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				changeLifepoints(lifePointsDX, "-");
			}
		});

		// Plus SX button
		final Button buttonPlusSX = (Button) findViewById(R.id.buttonPlusSX);
		buttonPlusSX.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				changeLifepoints(lifePointsSX, "+");
			}
		});

		// Plus DX button
		final Button buttonPlusDX = (Button) findViewById(R.id.buttonPlusDX);
		buttonPlusDX.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				changeLifepoints(lifePointsDX, "+");
			}
		});

		// 1 number button
		final Button button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				changeDamage("1");
			}
		});

		// 2 number button
		final Button button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				changeDamage("2");
			}
		});

		// 3 number button
		final Button button3 = (Button) findViewById(R.id.button3);
		button3.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				changeDamage("3");
			}
		});

		// 4 number button
		final Button button4 = (Button) findViewById(R.id.button4);
		button4.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				changeDamage("4");
			}
		});

		// 5 number button
		final Button button5 = (Button) findViewById(R.id.button5);
		button5.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				changeDamage("5");
			}
		});

		// 6 number button
		final Button button6 = (Button) findViewById(R.id.button6);
		button6.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				changeDamage("6");
			}
		});

		// 7 number button
		final Button button7 = (Button) findViewById(R.id.button7);
		button7.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				changeDamage("7");
			}
		});

		// 8 number button
		final Button button8 = (Button) findViewById(R.id.button8);
		button8.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				changeDamage("8");
			}
		});

		// 9 number button
		final Button button9 = (Button) findViewById(R.id.button9);
		button9.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				changeDamage("9");
			}
		});

		// 0 number button
		final Button button0 = (Button) findViewById(R.id.button0);
		button0.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				addZero("0");
			}
		});

		// 00 number button
		final Button button00 = (Button) findViewById(R.id.button00);
		button00.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				addZero("00");
			}
		});

		// 000 number button
		final Button button000 = (Button) findViewById(R.id.button000);
		button000.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				addZero("000");
			}
		});

	}

	// increase o decrese the lifepoints of the player 
	protected void changeLifepoints(TextView lifePointsView, String option) {
		int lifePointsValue = Integer.parseInt((String) lifePointsView.getText());
		int damageValue = Integer.parseInt((String) this.damage.getText());
		
		if (option.equals("+")) {			
			lifePointsValue = lifePointsValue + damageValue;		
			playSound("healt_sound.mp3");
			
		} else {
			lifePointsValue = lifePointsValue - damageValue;	
			
			if (lifePointsValue<=0) {
				lifePointsValue = 0;
				playSound("boom_sound.mp3");				
			} else {
				
				if (damageValue<=3000){
					playSound("low_damage.mp3");
				}
				if (damageValue>6000){
					playSound("high_damage.mp3");
				}
				playSound("middle_damage.mp3");				
			}			
		}
		
		lifePointsView.setText(Integer.toString(lifePointsValue));
		this.damage.setText("0");
		updateBar();
	}

	// View the message with the name of winner player
	private void updateBar() {
		String pointsSX = this.lifePointsSX.getText().toString();
		String pointsDX = this.lifePointsDX.getText().toString();
		
		this.progressBarDX.setProgress(Integer.parseInt(pointsDX));
		this.progressBarSX.setProgress(Integer.parseInt(pointsSX));
		
		if (pointsDX.equals("0")) {
			Toast.makeText(this, this.usernameSX.getText().toString() + " win the game!" , Toast.LENGTH_SHORT).show();
			return;
		}
		
		if (pointsSX.equals("0")) {
			Toast.makeText(this, this.usernameDX.getText().toString() + " win the game!" , Toast.LENGTH_SHORT).show();
			return;
		}
		
	}

	// Concat multiple 0 at the damege
	protected void addZero(String value) {
		playSound("click_sound.mp3");
		String damageValue = (String) this.damage.getText();
		if (!damageValue.equals("0")) {
			damageValue = damageValue.concat(value);
		}

		if (Integer.parseInt(damageValue) > 99999) {
			damageValue = "99999";
		}

		this.damage.setText(damageValue);

	}

	// Add the number at the and of damage string
	protected void changeDamage(String value) {
		playSound("click_sound.mp3");
		String damageValue = (String) this.damage.getText();
		if (damageValue.equals("0")) {
			damageValue = value;
		} else {
			damageValue = damageValue.concat(value);
		}

		if (Integer.parseInt(damageValue) > 99999) {
			damageValue = "99999";
		}
		this.damage.setText(damageValue);
	}

	// Play a sound of assert fold
	protected void playSound(String soundName) {
		try {
			final AssetFileDescriptor descriptor = getAssets().openFd(soundName);
			MediaPlayer mediaPlayer = new MediaPlayer();

			mediaPlayer.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(),
					descriptor.getLength());
			descriptor.close();
			mediaPlayer.prepare();
			mediaPlayer.start();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
