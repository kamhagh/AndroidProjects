package de.vogella.android.socialapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class OverviewActivity extends Activity {
  protected Object mActionMode;
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    // define the contextual action mode
    View view = findViewById(R.id.myView);
    view.setOnLongClickListener(new View.OnLongClickListener() {
      // called when the user long-clicks on someView
      public boolean onLongClick(View view) {
        if (mActionMode != null) {
          return false;
        }

        // start the CAB using the ActionMode.Callback defined above
        mActionMode = OverviewActivity.this
            .startActionMode(mActionModeCallback);
        view.setSelected(true);
        return true;
      }
    });
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.overview, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    Toast.makeText(this, "Just a test", Toast.LENGTH_SHORT).show();
    return true;
  }

  private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {

    // Called when the action mode is created; startActionMode() was called
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
      // inflate a menu resource providing context menu items
      MenuInflater inflater = mode.getMenuInflater();
      // assumes that you have "contexual.xml" menu resources
      inflater.inflate(R.menu.contextual, menu);
      return true;
    }

    // called each time the action mode is shown. Always called after
    // onCreateActionMode, but
    // may be called multiple times if the mode is invalidated.
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
      return false; // Return false if nothing is done
    }

    // called when the user selects a contextual menu item
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
      switch (item.getItemId()) {
      case R.id.toast:
        Toast.makeText(OverviewActivity.this, "Selected menu",
            Toast.LENGTH_LONG).show();
        mode.finish(); // Action picked, so close the CAB
        return true;
      default:
        return false;
      }
    }

    // called when the user exits the action mode
    public void onDestroyActionMode(ActionMode mode) {
      mActionMode = null;
    }
  };

} 