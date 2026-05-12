package com.mindmatrix.raithavarta.data;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@SuppressWarnings({"unchecked", "deprecation"})
public final class TipDao_Impl implements TipDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<TipEntity> __insertionAdapterOfTipEntity;

  public TipDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTipEntity = new EntityInsertionAdapter<TipEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `tips` (`id`,`category`,`instruction`,`tipImage`,`isSuccessStory`,`farmerName`,`kannadaInstruction`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @Nullable final TipEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getCategory() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getCategory());
        }
        if (entity.getInstruction() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getInstruction());
        }
        if (entity.getTipImage() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getTipImage());
        }
        final int _tmp = entity.isSuccessStory() ? 1 : 0;
        statement.bindLong(5, _tmp);
        if (entity.getFarmerName() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getFarmerName());
        }
        if (entity.getKannadaInstruction() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getKannadaInstruction());
        }
      }
    };
  }

  @Override
  public Object insertTips(final List<TipEntity> tips,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfTipEntity.insert(tips);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<TipEntity>> getTipsByCategory(final String category) {
    final String _sql = "SELECT * FROM tips WHERE category = ? OR ? = 'All'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (category == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, category);
    }
    _argIndex = 2;
    if (category == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, category);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"tips"}, new Callable<List<TipEntity>>() {
      @Override
      @NonNull
      public List<TipEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfInstruction = CursorUtil.getColumnIndexOrThrow(_cursor, "instruction");
          final int _cursorIndexOfTipImage = CursorUtil.getColumnIndexOrThrow(_cursor, "tipImage");
          final int _cursorIndexOfIsSuccessStory = CursorUtil.getColumnIndexOrThrow(_cursor, "isSuccessStory");
          final int _cursorIndexOfFarmerName = CursorUtil.getColumnIndexOrThrow(_cursor, "farmerName");
          final int _cursorIndexOfKannadaInstruction = CursorUtil.getColumnIndexOrThrow(_cursor, "kannadaInstruction");
          final List<TipEntity> _result = new ArrayList<TipEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final TipEntity _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final String _tmpInstruction;
            if (_cursor.isNull(_cursorIndexOfInstruction)) {
              _tmpInstruction = null;
            } else {
              _tmpInstruction = _cursor.getString(_cursorIndexOfInstruction);
            }
            final String _tmpTipImage;
            if (_cursor.isNull(_cursorIndexOfTipImage)) {
              _tmpTipImage = null;
            } else {
              _tmpTipImage = _cursor.getString(_cursorIndexOfTipImage);
            }
            final boolean _tmpIsSuccessStory;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsSuccessStory);
            _tmpIsSuccessStory = _tmp != 0;
            final String _tmpFarmerName;
            if (_cursor.isNull(_cursorIndexOfFarmerName)) {
              _tmpFarmerName = null;
            } else {
              _tmpFarmerName = _cursor.getString(_cursorIndexOfFarmerName);
            }
            final String _tmpKannadaInstruction;
            if (_cursor.isNull(_cursorIndexOfKannadaInstruction)) {
              _tmpKannadaInstruction = null;
            } else {
              _tmpKannadaInstruction = _cursor.getString(_cursorIndexOfKannadaInstruction);
            }
            _item = new TipEntity(_tmpId,_tmpCategory,_tmpInstruction,_tmpTipImage,_tmpIsSuccessStory,_tmpFarmerName,_tmpKannadaInstruction);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getCount(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM tips";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
