'use client';

import { useAuth } from '@/contexts/AuthContext';
import { useEffect, useState } from 'react';
import { getDenuncias, getOrgaos } from '../api/admin';

interface Stats {
  totalDenuncias: number;
  denunciasPendentes: number;
  orgaosCadastrados: number;
}

export default function AdminDashboard() {
  const { user } = useAuth();
  const [stats, setStats] = useState<Stats>({
    totalDenuncias: 0,
    denunciasPendentes: 0,
    orgaosCadastrados: 0,
  });

  useEffect(() => {
    const fetchStats = async () => {
      try {
        const [denuncias, orgaos] = await Promise.all([
          getDenuncias(),
          getOrgaos(),
        ]);

        setStats({
          totalDenuncias: denuncias.length,
          denunciasPendentes: denuncias.filter((d: any) => !d.feedback).length,
          orgaosCadastrados: orgaos.length,
        });
      } catch (error) {
        console.error('Error fetching stats:', error);
      }
    };

    fetchStats();
  }, []);

  return (
    <div className="space-y-6">
      <div className="bg-white shadow px-4 py-5 sm:rounded-lg sm:p-6">
        <div className="md:grid md:grid-cols-3 md:gap-6">
          <div className="md:col-span-1">
            <h3 className="text-lg font-medium leading-6 text-gray-900">
              Bem-vindo, {user?.nome}
            </h3>
            <p className="mt-1 text-sm text-gray-500">
              Este é o painel administrativo do sistema.
            </p>
          </div>
          <div className="mt-5 md:mt-0 md:col-span-2">
            <div className="grid grid-cols-1 gap-5 sm:grid-cols-2 lg:grid-cols-3">
              <div className="bg-white overflow-hidden shadow rounded-lg">
                <div className="px-4 py-5 sm:p-6">
                  <dt className="text-sm font-medium text-gray-500 truncate">
                    Total de Denúncias
                  </dt>
                  <dd className="mt-1 text-3xl font-semibold text-gray-900">
                    {stats.totalDenuncias}
                  </dd>
                </div>
              </div>

              <div className="bg-white overflow-hidden shadow rounded-lg">
                <div className="px-4 py-5 sm:p-6">
                  <dt className="text-sm font-medium text-gray-500 truncate">
                    Denúncias Pendentes
                  </dt>
                  <dd className="mt-1 text-3xl font-semibold text-gray-900">
                    {stats.denunciasPendentes}
                  </dd>
                </div>
              </div>

              <div className="bg-white overflow-hidden shadow rounded-lg">
                <div className="px-4 py-5 sm:p-6">
                  <dt className="text-sm font-medium text-gray-500 truncate">
                    Órgãos Cadastrados
                  </dt>
                  <dd className="mt-1 text-3xl font-semibold text-gray-900">
                    {stats.orgaosCadastrados}
                  </dd>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
} 