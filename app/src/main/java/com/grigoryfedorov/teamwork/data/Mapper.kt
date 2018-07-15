package com.grigoryfedorov.teamwork.data

interface Mapper<in SrcModel, out OutModel> {
    fun map(srcModel: SrcModel): OutModel
}