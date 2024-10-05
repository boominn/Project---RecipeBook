package com.example.project_mealsbook.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_mealsbook.model.Besin
import com.example.project_mealsbook.roomdb.BesinDatabase
import com.example.project_mealsbook.service.BesinAPIServis
import com.example.project_mealsbook.util.OzelSharedPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BesinListesiViewModel(application: Application): AndroidViewModel(application) {

    val besinler = MutableLiveData<List<Besin>>()
    val besinHataMesaji = MutableLiveData<Boolean>()
    val besinYukleniyor = MutableLiveData<Boolean>()



    private val besinApiServis = BesinAPIServis()
    private val ozelSharedPreferences = OzelSharedPreferences(getApplication())

    private val guncellemeZamani = 10 * 60 * 1000 * 1000 * 1000L

    fun refreshData() {
        val kaydedilmeZamani = ozelSharedPreferences.zamaniAl()

        if(kaydedilmeZamani != null  && kaydedilmeZamani != 0L && System.nanoTime() - kaydedilmeZamani < guncellemeZamani) {
            //roomdan verileri alıcaz
        }
        else {
            verileriInternettenAl()
        }

    }
    fun refreshDataFromInternet() {
        verileriInternettenAl()

    }
    private fun verileriRoomdanAl() {
        besinYukleniyor.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val besinListesi = BesinDatabase(getApplication()).besinDao().getAllBesin()
            withContext(Dispatchers.Main) {
                besinleriGoster(besinListesi)
                Toast.makeText(getApplication(), "Besinleri Room'dan aldık",Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun verileriInternettenAl() {
        viewModelScope.launch(Dispatchers.IO) {
            val besinListesi = besinApiServis.getData()
            withContext(Dispatchers.Main) {
                besinYukleniyor.value = true
                roomaKaydet(besinListesi)
                Toast.makeText(getApplication(),"Besinleri Internetten Aldık",Toast.LENGTH_LONG).show()
            }
        }


    }

    private fun besinleriGoster(besinListesi: List<Besin>) {
        besinler.value = besinListesi
        besinHataMesaji.value = false
        besinYukleniyor.value = false

    }
    private fun roomaKaydet(besinListesi: List<Besin>) {
                // roomdan aldığımız verileri önce delete ile cashleri temizliyoruz daha sonra fonksiyona
        // alacağımız besin listesini parametre olarak koyuyoruz netten çektiğimiz listeyi parametre olarak aldıktan sonra
        // insertAll fonksiyonu bize long veri tipinde bir id listesi döndürdüğü için onu uuidListesine
        // eşitliyoruz daha sonra besinListesindeki her elemanın uuid değerini manuel olarak internetten çekilen
        // verilerin idlerine eşitliyoruz.
        viewModelScope.launch {
            val dao = BesinDatabase(getApplication()).besinDao()
            dao.deleteAll()
            val uuidListesi = dao.insertAll(*besinListesi.toTypedArray())
            var i = 0
            while(i < uuidListesi.size) {
                besinListesi[i].uuid = uuidListesi[i].toInt()
                i = i+1
            }
            besinleriGoster(besinListesi)

        }
        ozelSharedPreferences.zamaniKaydet(System.nanoTime())
    }
}






