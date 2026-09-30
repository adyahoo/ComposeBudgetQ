package com.example.budgetlimittracking.di

import com.example.budgetlimittracking.data.datasource.LocalDataSource
import com.example.budgetlimittracking.data.repository.BudgetRepositoryImpl
import com.example.budgetlimittracking.data.repository.ExpenseRepositoryImpl
import com.example.budgetlimittracking.domain.repository.BudgetRepository
import com.example.budgetlimittracking.domain.repository.ExpenseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalDataSource(): LocalDataSource {
        return LocalDataSource()
    }

    @Provides
    @Singleton
    fun provideBudgetRepository(
        impl: BudgetRepositoryImpl
    ): BudgetRepository = impl

    @Provides
    @Singleton
    fun provideExpenseRepository(
        impl: ExpenseRepositoryImpl
    ): ExpenseRepository = impl
}
