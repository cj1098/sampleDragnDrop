package com.example.sampledragndrop

import android.annotation.SuppressLint
import androidx.compose.runtime.Stable
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.burnoutcrew.reorderable.move
import java.util.*


@Stable
data class ItemData(
    val title: String,
    val key: String,
    val isLocked: Boolean = false,
    val url: String = ""
)

class ReorderListViewModel : ViewModel(), DragAndDropActions {

    val uiState: DragAndDropUiState
        get() = uiStateLiveData.value ?: throw IllegalStateException("UiState is null")
    protected val _uiStateLiveData: MutableLiveData<DragAndDropUiState> = MutableLiveData()
    val uiStateLiveData: LiveData<DragAndDropUiState> get() = _uiStateLiveData

    init {
        _uiStateLiveData.value = DragAndDropUiState()
        createDogList()
    }

    fun moveDog(from: Int, to: Int) {
        val swapList = uiState.items.toMutableStateList()
        Collections.swap(swapList, from, to)
        updateUiState { uiState ->
            uiState.copy(items = swapList)
        }
    }

    override fun moveItem(from: Int, to: Int) {
        val swapList = uiState.items.toMutableStateList()
        Collections.swap(swapList, from, to)
        updateUiState { uiState ->
            uiState.copy(items = swapList)
        }
    }

    private fun createDogList() {
        val dogList = emptyList<ItemData>().toMutableList()

        dogList.add(
            ItemData(
                "Dog 0",
                "id0",
                false,
                "https://i.natgeofe.com/n/4f5aaece-3300-41a4-b2a8-ed2708a0a27c/domestic-dog_thumb_4x3.jpg"
            )
        )
        dogList.add(
            ItemData(
                "Dog 1",
                "id1",
                false,
                "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/dog-puppy-on-garden-royalty-free-image-1586966191.jpg?crop=1.00xw:0.669xh;0,0.190xh&resize=1200:*"
            )
        )
        dogList.add(
            ItemData(
                "Dog 2",
                "id2",
                false,
                "https://www.guidedogs.org/wp-content/uploads/2019/11/website-donate-mobile.jpg"
            )
        )
        dogList.add(
            ItemData(
                "Dog 3",
                "id3",
                false,
                "https://i.guim.co.uk/img/media/fe1e34da640c5c56ed16f76ce6f994fa9343d09d/0_174_3408_2046/master/3408.jpg?width=1200&height=900&quality=85&auto=format&fit=crop&s=0d3f33fb6aa6e0154b7713a00454c83d"
            )
        )
        dogList.add(
            ItemData(
                "Dog 4",
                "id4",
                false,
                "https://post.medicalnewstoday.com/wp-content/uploads/sites/3/2020/02/322868_1100-800x825.jpg"
            )
        )
        dogList.add(
            ItemData(
                "Dog 5",
                "id5",
                false,
                "https://i.natgeofe.com/n/3faa2b6a-f351-4995-8fff-36d145116882/domestic-dog_16x9.jpg?w=1200"
            )
        )
        dogList.add(
            ItemData(
                "Dog 6",
                "id6",
                false,
                "https://static01.nyt.com/images/2019/06/17/science/17DOGS/17DOGS-mobileMasterAt3x-v2.jpg"
            )
        )
        dogList.add(
            ItemData(
                "Dog 7",
                "id7",
                false,
                "https://www.thesprucepets.com/thmb/sfuyyLvyUx636_Oq3Fw5_mt-PIc=/3760x2820/smart/filters:no_upscale()/adorable-white-pomeranian-puppy-spitz-921029690-5c8be25d46e0fb000172effe.jpg"
            )
        )
        dogList.add(
            ItemData(
                "Dog 8",
                "id8",
                false,
                "https://s3.amazonaws.com/cdn-origin-etr.akc.org/wp-content/uploads/2018/01/12201051/cute-puppy-body-image.jpg"
            )
        )
        dogList.add(
            ItemData(
                "Dog 9",
                "id9",
                false,
                "https://cbsnews2.cbsistatic.com/hub/i/r/2020/10/15/d706469d-10ee-4444-aa88-cbaa791ee6b4/thumbnail/1200x630/4de7387aa5934fc53bdaca530946822b/01-171014-schmitz-16.jpg"
            )
        )
        dogList.add(
            ItemData(
                "Dog 10",
                "id10",
                false,
                "http://cdn.akc.org/content/article-body-image/lab_puppy_dog_pictures.jpg"
            )
        )
        dogList.add(
            ItemData(
                "Dog 11",
                "id11",
                false,
                "https://c.files.bbci.co.uk/E361/production/_117090285_mediaitem117090284.jpg"
            )
        )
        dogList.add(
            ItemData(
                "Dog 12",
                "id12",
                false,
                "https://img.webmd.com/dtmcms/live/webmd/consumer_assets/site_images/article_thumbnails/slideshows/surprises_about_dogs_and_cats_slideshow/1800x1200_surprises_about_dogs_and_cats_slideshow.jpg"
            )
        )
        dogList.add(
            ItemData(
                "Dog 13",
                "id13",
                false,
                "https://www.cdc.gov/healthypets/images/pets/cute-dog-headshot.jpg?_=42445"
            )
        )
        dogList.add(
            ItemData(
                "Dog 14",
                "id14",
                false,
                "hhttps://arc-anglerfish-washpost-prod-washpost.s3.amazonaws.com/public/HB4AT3D3IMI6TMPTWIZ74WAR54.jpg"
            )
        )
        dogList.add(
            ItemData(
                "Dog 15",
                "id15",
                false,
                "https://cdn.psychologytoday.com/sites/default/files/styles/article-inline-half-caption/public/field_blog_entry_images/2020-04/cb.jpg?itok=zzuVtGPr"
            )
        )
        dogList.add(
            ItemData(
                "Dog 16",
                "id16",
                false,
                "https://www.akcpetinsurance.com/res/akc/images/icons/home/home_dog.png"
            )
        )

        updateUiState { uiState ->
            uiState.copy(items = dogList.toMutableStateList())
        }
    }

    @SuppressLint("RestrictedApi")
    protected fun updateUiState(update: (DragAndDropUiState) -> DragAndDropUiState) {
        uiStateLiveData.value?.let {
            val newModel = update(it)

            check(!(newModel === uiStateLiveData.value)) { "UiModel is the same object. Use .copy" }

            _uiStateLiveData.value = newModel
        } ?: throw IllegalStateException("UiModel is null")
    }

}

