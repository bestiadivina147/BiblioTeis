package com.example.biblioteis.ViewModels;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.biblioteis.API.models.Book;
import com.example.biblioteis.API.models.BookLending;
import com.example.biblioteis.API.repository.BookLendingRepository;
import com.example.biblioteis.API.repository.BookRepository;
import com.example.biblioteis.API.repository.ImageRepository;
import com.example.biblioteis.mapper.BookMapper;
import com.example.biblioteis.models.Libro;
import com.example.biblioteis.models.LibroDetalle;
import com.example.biblioteis.utils.DateUtils;
import com.google.gson.Gson;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import okhttp3.ResponseBody;

public class DetalleVM extends ViewModel {
    public MutableLiveData<LibroDetalle> librosLD= new MutableLiveData<>();

    public BookRepository bookRepository = new BookRepository();
    public ImageRepository imageRepository = new ImageRepository();
    public BookLendingRepository bookLendingRepository = new BookLendingRepository();


    public void load(int id) {
        bookRepository.getBookById(id, new BookRepository.ApiCallback<Book>() {
            @Override
            public void onSuccess(Book result) {
                LibroDetalle libro = BookMapper.book2LibroDetalle(result);
                librosLD.setValue(libro);
                obtenLibroImagen(result.getBookPicture(), libro);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

    }

    private void obtenLibroImagen(String urlImg, LibroDetalle libro) {
        if(urlImg==null){
            return;
        }
        imageRepository.getImage(urlImg, new BookRepository.ApiCallback<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody result) {
                if (result == null){
                    return;
                }
                Bitmap bm = BitmapFactory.decodeStream(result.byteStream());
                libro.setImg(bm);
                librosLD.postValue(libro);
            }

            @Override
            public void onFailure(Throwable t) {
                //TODO
                Log.e("LibreriaVM", "Error load books", t);
            }
        });
    }

    public void prestarLibro(int libroId, int usuarioId) {
        if (usuarioId == -1 || libroId == -1) {
            Log.e("DetalleVM", "Error: Usuario o libro inválido para préstamo.");
            return;
        }

        // Obtener el libro antes de realizar el préstamo
        bookRepository.getBookById(libroId, new BookRepository.ApiCallback<Book>() {
            @Override
            public void onSuccess(Book book) {
                if (book == null) {
                    Log.e("DetalleVM", "Error: No se pudo obtener el libro.");
                    return;
                }

                // Crear el objeto `BookLending` con el libro asignado
                BookLending nuevoPrestamo = new BookLending();
                nuevoPrestamo.setBookId(libroId);
                nuevoPrestamo.setUserId(usuarioId);
                nuevoPrestamo.setLendDate(DateUtils.getCurrentDate());
                try {
                    Date date = DateUtils.String2Date(nuevoPrestamo.getLendDate());
                    Date returndate = DateUtils.sumarDiasAFecha(date,15);
                    nuevoPrestamo.setReturnDate(returndate.toString());
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                nuevoPrestamo.setBook(book); // Asignar el libro al préstamo

                // Enviar la solicitud de préstamo
                bookLendingRepository.lendBook(nuevoPrestamo, new BookRepository.ApiCallback<Boolean>() {
                    @Override
                    public void onSuccess(Boolean result) {
                        if (result) {
                            Log.i("DetalleVM", "Datos del préstamo enviado: " + new Gson().toJson(nuevoPrestamo));

                            Log.i("DetalleVM", "Libro prestado con éxito.");
                            load(libroId); // Recargar datos del libro para actualizar la UI
                        } else {
                            Log.i("DetalleVM", "Datos del préstamo enviado: " + new Gson().toJson(nuevoPrestamo));

                            Log.e("DetalleVM", "Error al prestar el libro.");
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Log.e("DetalleVM", "Fallo al conectar con el servidor para prestar libro", t);
                    }
                });
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("DetalleVM", "Error al obtener el libro para préstamo", t);
            }
        });
    }


    public void devolverLibro(int libroId, int usuarioId) {
        // 1) Validaciones básicas
        if (libroId < 0 || usuarioId < 0) {
            Log.e("DetalleVM", "IDs inválidos para devolución (libroId=" + libroId + ", usuarioId=" + usuarioId + ")");
            return;
        }

        // 2) Recuperar todos los préstamos para encontrar el activo de este libro y usuario
        bookLendingRepository.getAllLendings(new BookRepository.ApiCallback<List<BookLending>>() {
            @Override
            public void onSuccess(List<BookLending> allLendings) {
                BookLending activo = null;
                for (BookLending bl : allLendings) {
                    if (bl.getBookId() == libroId
                            && bl.getUserId() == usuarioId
                            && (bl.getReturnDate() == null || bl.getReturnDate().isEmpty())) {
                        activo = bl;
                        break;
                    }
                }

                if (activo == null) {
                    Log.e("DetalleVM", "No se encontró préstamo activo para libro "
                            + libroId + " y usuario " + usuarioId);
                    return;
                }

                int lendingId = activo.getId();
                // 3) Llamar al repositorio para devolverlo
                bookLendingRepository.returnBook(lendingId, new BookRepository.ApiCallback<Boolean>() {
                    @Override
                    public void onSuccess(Boolean result) {
                        Log.i("DetalleVM", "returnBook result=" + result);
                        // Refresca la UI
                        load(libroId);
                    }
                    @Override
                    public void onFailure(Throwable t) {
                        Log.e("DetalleVM", "Error al devolver libro", t);
                    }
                });
            }
            @Override
            public void onFailure(Throwable t) {
                Log.e("DetalleVM", "Error al obtener lista de préstamos", t);
            }
        });
    }




}
