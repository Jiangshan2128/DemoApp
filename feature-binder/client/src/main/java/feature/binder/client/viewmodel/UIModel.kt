package feature.binder.client.viewmodel

sealed class ClientUiModel
object Loading : ClientUiModel()
data class UIModel(
    val serviceConnected: Boolean = false,
    val message: String
): ClientUiModel()